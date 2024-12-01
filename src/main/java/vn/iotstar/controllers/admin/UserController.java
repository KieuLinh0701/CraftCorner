package vn.iotstar.controllers.admin;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.iotstar.entity.User;
import vn.iotstar.services.*;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.*;

@MultipartConfig
@WebServlet(urlPatterns = {
    "/admin/users",
    "/admin/user/add",
    "/admin/user/insert",
    "/admin/user/edit",
    "/admin/user/update",
    "/admin/user/delete"
})
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/admin/users")) {
            List<User> users = userService.findAllCustomers();
            req.setAttribute("listUsers", users);
            req.getRequestDispatcher("/views/userlist.jsp").forward(req, resp);
        } else if (url.contains("/admin/user/add")) {
            req.getRequestDispatcher("/views/useradd.jsp").forward(req, resp);
        } else if (url.contains("/admin/user/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            User user = userService.findById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/useredit.jsp").forward(req, resp);
        } 
//            else if (url.contains("/admin/user/delete")) {
//            int id = Integer.parseInt(req.getParameter("id"));
//            userService.delete(id);
//            resp.sendRedirect(req.getContextPath() + "/admin/users");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/admin/user/insert")) {
            // Lấy dữ liệu từ form
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");
            int status = Integer.parseInt(req.getParameter("status"));

            User user = new User();
            user.setFullname(fullname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            user.setStatus(status);

            // Upload ảnh nếu có
            String uploadPath = Constant.DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            Part part = req.getPart("image");
            if (part.getSize() > 0) {
                String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
                part.write(uploadPath + "/" + fileName);
                user.setImage(fileName);
            } else {
                user.setImage("default-avatar.png");
            }

            userService.insert(user);
            resp.sendRedirect(req.getContextPath() + "/admin/users");
        } else if (url.contains("/admin/user/update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            int status = Integer.parseInt(req.getParameter("status"));

            User user = userService.findById(id);
            user.setFullname(fullname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setStatus(status);

            userService.update(user);
            resp.sendRedirect(req.getContextPath() + "/admin/users");
        }
    }

}
