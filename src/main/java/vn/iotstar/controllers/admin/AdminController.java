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
import java.time.LocalDateTime;
import java.util.List;

import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.services.*;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.*;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/adminsmanage",
    "/admin/adminsmanage/add",
    "/admin/adminsmanage/insert",
    "/admin/adminsmanage/edit",
    "/admin/adminsmanage/update",
    "/admin/adminsmanage/delete"
})
public class AdminController extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/admin/adminsmanage")) {
        	List<User> admin = userService.findAllAdmin();
            req.setAttribute("listAdmins", admin);
            req.getRequestDispatcher("/views/admin/AdminManagement/adminlist.jsp").forward(req, resp);
        }
        else if (url.contains("/admin/adminsmanage/add")) {
            req.getRequestDispatcher("/views/admin/AdminManagement/adminadd.jsp").forward(req, resp);
        }
        else if (url.contains("/admin/user/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            User user = userService.findById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/useredit.jsp").forward(req, resp);
        }
        else if (url.contains("/admin/user/delete")) {
            try {
                // Lấy ID người dùng từ tham số request
                int id = Integer.parseInt(req.getParameter("id"));
                
                // Gọi phương thức xóa người dùng từ service
                userService.delete(id);
                
                // Sau khi xóa thành công, chuyển hướng về danh sách người dùng
                resp.sendRedirect(req.getContextPath() + "/admin/users");
            } catch (NumberFormatException e) {
                // Nếu không thể chuyển ID thành số nguyên, gửi lỗi cho người dùng
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format.");
            } catch (Exception e) {
                // Xử lý lỗi khác nếu xảy ra trong quá trình xóa người dùng
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the user.");
            }
       

            resp.sendRedirect(req.getContextPath() + "/admin/users");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String url = req.getRequestURI();
    	///admin/adminsmanage/insert
        try {
            if (url.contains("/admin/AdminManagement/adminadd.jsp")) {
                // Lấy dữ liệu từ form
                String fullname = req.getParameter("fullname");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                String password = req.getParameter("password");
                String statusStr = req.getParameter("status");
                int status = (statusStr != null && !statusStr.isEmpty()) ? Integer.parseInt(statusStr) : 1; // Mặc định status = 1

                // Tạo đối tượng User
                User user = new User();
                user.setFullname(fullname);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(password);
                user.setStatus(status);
                user.setCreateDate(LocalDateTime.now()); // Gán ngày tạo hiện tại

                // Thêm role vào user
                Role roleObj = new Role();
                roleObj.setId(1); // Set ID của Role Admin
                user.setRole(roleObj);

                // Upload ảnh nếu có
                String uploadPath = getServletContext().getRealPath("/") + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();

                Part part = req.getPart("image");
                if (part != null && part.getSize() > 0) {
                    String fileName = System.currentTimeMillis() + "_" + part.getSubmittedFileName();
                    part.write(uploadPath + "/" + fileName);
                    user.setImage(fileName);
                } else {
                    user.setImage("default-avatar.png"); // Ảnh mặc định nếu không upload
                }

                // Gọi service để lưu vào cơ sở dữ liệu
                userService.insert(user);

                // Điều hướng về danh sách users
                resp.sendRedirect(req.getContextPath() + "/admin/adminsmanage");

            } else if (url.contains("/admin/user/update")) {
                // Lấy dữ liệu từ form
                int id = Integer.parseInt(req.getParameter("id"));
                String fullname = req.getParameter("fullname");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                String statusStr = req.getParameter("status");
                int status = (statusStr != null && !statusStr.isEmpty()) ? Integer.parseInt(statusStr) : 1;

                // Tìm user cần cập nhật
                User user = userService.findById(id);
                if (user != null) {
                    user.setFullname(fullname);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setStatus(status);

                    // Gọi service để cập nhật user
                    userService.update(user);

                    // Điều hướng về danh sách users
                    resp.sendRedirect(req.getContextPath() + "/admin/adminsmanage");
                } else {
                    req.setAttribute("errorMessage", "User không tồn tại!");
                    //req.getRequestDispatcher("/error-page.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
           // req.getRequestDispatcher("/error-page.jsp").forward(req, resp);
        }
    }
}
