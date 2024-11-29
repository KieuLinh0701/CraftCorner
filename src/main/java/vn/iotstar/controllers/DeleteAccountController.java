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

@WebServlet(urlPatterns = { "/deleteAccount"})
public class DeleteAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
	        resp.setCharacterEncoding("UTF-8");

	        HttpSession session = req.getSession();
	        User user = (User) session.getAttribute("account");

	        if (user == null) {
	            String alertMsg = "User not logged in!";
	            req.setAttribute("alert", alertMsg);
	            req.getRequestDispatcher(Constant.ACCOUNT).forward(req, resp);
	            return;
	        }
            

	        else {
            	IUserService userService = new UserService();              
                // Xóa tài khoản
                userService.delete(user.getId());
                // Xóa session và chuyển hướng về trang chủ
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + "/home");
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }

}
