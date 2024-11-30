package vn.iotstar.controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;
@WebServlet("/admin/changePassword")
public class ChangePasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService(); // Tạo đối tượng UserService

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            String alertMsg = "User not logged in!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
            return;
        }

     // Lấy thông tin từ form
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String retypeNewPassword = req.getParameter("retypeNewPassword");

        String alertMsg = "";

        // Kiểm tra mật khẩu hiện tại có đúng không
        if (!userService.checkPassword(user.getEmail(), currentPassword)) {
            alertMsg = "Current password is incorrect!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
            return;
        }

        // Kiểm tra mật khẩu mới và mật khẩu nhập lại có khớp không
        if (!newPassword.equals(retypeNewPassword)) {
        	alertMsg = "New password and retype password do not match!";
        	req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
            return;
        }
        // Cập nhật thông tin người dùng        
        user.setPassword(newPassword);


        userService.update(user);

        alertMsg = "Your new password updated successfully!";
        session.setAttribute("account", user);
        req.setAttribute("alert", alertMsg);
        req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
        
    }
}