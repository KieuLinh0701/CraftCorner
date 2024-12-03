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
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.services.*;
import vn.iotstar.services.implement.RoleService;
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
    private IRoleService roleService = new RoleService();
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
        else if (url.contains("/admin/adminsmanage/edit")) {
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
            if (url.contains("/admin/AdminManagement/insert")) {
                // Lấy dữ liệu từ form
            	User user = new User();
            	String email = req.getParameter("email");
        		String fullname = req.getParameter("fullname");
        		String phone = req.getParameter("phone");
        		String password = req.getParameter("password");
         		String address_id = req.getParameter("address_id");
                int status = Integer.parseInt(req.getParameter("status"));
                int role = Integer.parseInt(req.getParameter("role"));
                //int status = (statusStr != null && !statusStr.isEmpty()) ? Integer.parseInt(statusStr) : 1; // Mặc định status = 1
                //String roleid = req.getParameter("role");
               // int role = (roleid != null && !roleid.isEmpty()) ? Integer.parseInt(roleid) : 1; // Mặc định status = 1
                // Tạo đối tượng User
                user.setFullname(fullname);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(password);
                user.setPassword(address_id);
                user.setStatus(status);
                user.setStatus(role);
                
                String fname = "";
                String uploadPath = Constant.UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                try {
                	Part part = req.getPart("image");
                	if(part.getSize()>0) {
                		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                		int index = fileName.lastIndexOf(".");
                		String ext = fileName.substring(index+1);
                		fname = System.currentTimeMillis() + "." + ext;
                		// upload file 
                		part.write(uploadPath + "/" + fileName);
                		// ghi tên file vào data
                	}else {
                		user.setImage("avatar.png");
                	}
                	
                }catch (Exception ex){
                	ex.printStackTrace();
                }
                
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
