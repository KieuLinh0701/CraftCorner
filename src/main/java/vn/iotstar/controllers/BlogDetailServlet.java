package vn.iotstar.controllers;

import vn.iotstar.dao.implement.BlogDAO;
import vn.iotstar.entity.Blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/blog-details")
public class BlogDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BlogDAO blogDAO;

	@Override
	public void init() {
		blogDAO = new BlogDAO(); // Khởi tạo DAO
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy tham số blog ID từ URL
			int blogId = Integer.parseInt(request.getParameter("id"));

			// Truy vấn blog từ cơ sở dữ liệu
			Blog blog = blogDAO.getBlogById(blogId);

			if (blog != null) {
				// Đặt blog vào request attribute
				request.setAttribute("blog", blog);

				// Chuyển tiếp đến blog-details.jsp
				request.getRequestDispatcher("/views/blog-details.jsp").forward(request, response);
			} else {
				// Nếu không tìm thấy blog, trả về lỗi 404
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Blog not found");
			}
		} catch (NumberFormatException e) {
			// Xử lý lỗi nếu ID không hợp lệ
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Blog ID");
		}
	}
}
