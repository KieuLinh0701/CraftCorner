package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.LOGIN).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Mã hóa bằng tiếng việt
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		// Lấy tham số từ view
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");

		// kiểm tra tham số
		boolean isRememberMe = false;
		if ("on".equals(remember)) {
			isRememberMe = true;
		}

		// biến kiểm tra đăng nhập thành công hay thất bại
		String alertMsg = "";
		
		// Xử lý bài toán
		User user = userService.findByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if (user.getStatus()!=1) {
				session = req.getSession();
				session.setAttribute("alertMessage", "Your account is waiting to be activated. Activate it now to get started!");
				resp.sendRedirect(req.getContextPath() + "/verifycodeaccount");
				return;
			}
			if (isRememberMe) {
				saveRemeberMe(resp, email);
			}
			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "Please check your username and password and try again";
			req.setAttribute("alert", alertMsg);
			req.setAttribute("email", email);
			req.getRequestDispatcher(Constant.LOGIN).forward(req, resp);
		}
	}

	private void saveRemeberMe(HttpServletResponse resp, String email) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, email);
		cookie.setMaxAge(30 * 60);
		resp.addCookie(cookie);
	}
}
