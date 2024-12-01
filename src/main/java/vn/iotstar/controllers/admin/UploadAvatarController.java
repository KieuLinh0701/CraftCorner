package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.User;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;


@WebServlet("/admin/uploadAvatar")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UploadAvatarController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "uploads";
    private UserService userService; // Khai báo userService

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();  // Khởi tạo UserService thủ công
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession();
	        User user = (User) session.getAttribute("account");

	        if (user == null) {
	            resp.sendRedirect(req.getContextPath() + "/admin/home");
	            return;
	        }

	        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }

	        for (Part part : req.getParts()) {
	            String fileName = extractFileName(part);
	            if (fileName != null && !fileName.isEmpty()) {
	                String filePath = uploadPath + File.separator + fileName;
	                part.write(filePath); // Lưu file

	                user.setImage(UPLOAD_DIR + "/" + fileName); // Cập nhật hình ảnh
	                userService.update(user); // Cập nhật thông tin người dùng
	                session.setAttribute("account", user); // Lưu lại thông tin người dùng trong session
	            }
	        }

	        session.setAttribute("account", user);
	        req.setAttribute("alert", "Your Avatar Profile updated successfully!");
	        req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
	    }

		private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        for (String content : contentDisp.split(";")) {
	            if (content.trim().startsWith("filename")) {
	                return content.substring(content.indexOf("=") + 2, content.length() - 1);
	            }
	        }
	        return null;
	    }
	}