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

@WebServlet(urlPatterns = {"/verifycodeaccount", "/verifycodepassword"})
public class EmailController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public IUserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setAttribute("alert", "We have sent a verification code to the email address associated with your account. Kindly check your inbox to retrieve it!");
		if (url.contains("verifycodeaccount")) {
			req.getRequestDispatcher(Constant.VERIFY_CODE_ACCOUNT).forward(req, resp);
		} else if (url.contains("verifycodepassword")){
			req.getRequestDispatcher(Constant.VERIFY_CODE_PASSWORD).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String code = req.getParameter("code");
		
		if (url.contains("verifycodeaccount")) {
			String alertMsg = "";
			if (userService.checkCode(email, code)) {
				IUserService userService = new UserService();
				User user = userService.findByEmail(email);
				int status = 1;
				user.setStatus(status);
				userService.update(user);
				HttpSession session = req.getSession();
				session.setAttribute("alertMessage", "Great! Your account is ready. Log in to start shopping!");
				resp.sendRedirect(req.getContextPath() + "/login");
			}	else {
				alertMsg = "Invalid verification code. Please re-enter!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher(Constant.VERIFY_CODE_ACCOUNT).forward(req, resp);
			}
		} else if (url.contains("verifycodepassword")) {
			String alertMsg = "";
			if (userService.checkCode(email, code)) {
				resp.sendRedirect(req.getContextPath() + "/createpassword");
			} else {
				alertMsg = "Invalid verification code. Please re-enter!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher(Constant.VERIFY_CODE_PASSWORD).forward(req, resp);
			}
		}
	}
}
