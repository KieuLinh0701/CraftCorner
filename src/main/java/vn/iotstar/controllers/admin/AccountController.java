package vn.iotstar.controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Address;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/admin/myaccount"})
public class AccountController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IUserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
		
	}
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

	        String email = req.getParameter("email");
	        String fullname = req.getParameter("fullname");
	        String phone = req.getParameter("phone");
	        String city = req.getParameter("city");
	        String district = req.getParameter("district");
	        String ward = req.getParameter("ward");
	        String detail = req.getParameter("detail");

	        String alertMsg = "";

//	        // Kiểm tra email và số điện thoại có bị trùng hay không
//	        if (!email.equals(user.getEmail())) {
//	            alertMsg = "Email is already in use!";
//	            req.setAttribute("alert", alertMsg);
//	            req.getRequestDispatcher(Constant.ACCOUNT).forward(req, resp);
//	            return;
//	        }
	        if (!phone.equals(user.getPhone()) && userService.checkExistPhone(phone)) {
	            alertMsg = "Phone number is already in use!";
	            req.setAttribute("alert", alertMsg);
	            req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
	            return;
	        }

	     // Cập nhật thông tin người dùng
	        user.setEmail(email);
	        user.setFullname(fullname);
	        user.setPhone(phone);

	        // Tạo hoặc cập nhật Address
	        Address address = user.getAddress(); // Lấy Address hiện tại của User nếu có
	        address.setCity(city);
	        address.setDistrict(district);
	        address.setWard(ward);
	        address.setDetail(detail);

	        // Gán lại Address cho User
	        user.setAddress(address);

	        // Cập nhật thông tin người dùng
	        userService.update(user);

	        alertMsg = "Profile updated successfully!";
	        session.setAttribute("account", user);
	        req.setAttribute("alert", alertMsg);
	        req.getRequestDispatcher(Constant.ADMIN_ACCOUNT).forward(req, resp);
	    }

}
