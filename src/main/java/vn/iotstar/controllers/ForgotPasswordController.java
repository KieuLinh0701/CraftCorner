package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/forgotpassword", "/createpassword" })
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	IUserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("forgotpassword")) {
			req.getRequestDispatcher(Constant.FORGOT_PASSWORD).forward(req, resp);
		} else if (url.contains("createpassword")) {
			req.getRequestDispatcher(Constant.CREATE_PASSWORD).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		// Mã hóa bằng tiếng việt
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		if (url.contains("forgotpassword")) {
			// Lấy tham số từ view
			String email = req.getParameter("email");

			// biến kiểm tra đổi mật khẩu thành công hay thất bại
			String alertMsg = "";
			
			// Xử lý bài toán
			User user = service.findByEmail(email);
			if (user != null) {
				HttpSession session = req.getSession();
				session.setAttribute("account", user);
				resp.sendRedirect(req.getContextPath() + "/verifycodepassword");
			} else {
				alertMsg = "The email address you entered is not associated with any existing account. Please try again or create a new account!";
				req.setAttribute("email", email);
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher(Constant.FORGOT_PASSWORD).forward(req, resp);
			}
		} else if (url.contains("createpassword")) {
			String password = req.getParameter("password");
			
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			user.setPassword(password);
			service.update(user);
			session.setAttribute("alertMessage", "Great! Your password has been updated successfully. Log in to start shopping!");
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
