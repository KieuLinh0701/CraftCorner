package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IProductService;
import vn.iotstar.services.implement.CategoryService;
import vn.iotstar.services.implement.ProductService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/categories/insert", "/admin/category/delete", "/admin/category/update" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICategoryService cateService = new CategoryService();
	private IProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/categories")) {
			List<Category> categories = cateService.findAll();

			// Tạo một Map để lưu số lượng sản phẩm theo danh mục
			Map<Integer, Integer> productCountMap = new HashMap<>();
			for (Category category : categories) {
				int productCount = productService.productCount(category.getCategory_id()); // Tính số lượng sản phẩm
				productCountMap.put(category.getCategory_id(), productCount); // Lưu vào Map
			}

			// Truyền dữ liệu danh mục và Map số lượng sản phẩm vào JSP
			req.setAttribute("categories", categories);
			req.setAttribute("productCountMap", productCountMap);
			req.getRequestDispatcher("/views/admin/category/category_list.jsp").forward(req, resp);
		} else if (url.contains("/admin/category/delete")) {
			int categoryId = Integer.parseInt(req.getParameter("category_id"));
			System.out.println("Category ID to delete: " + categoryId);
			cateService.delete(categoryId);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/categories/insert")) {
			String categoryName = req.getParameter("categoryName");

			Category newCategory = new Category();
			newCategory.setName(categoryName);
			cateService.insert(newCategory); 

			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("/admin/category/update")) {
			// Xử lý cập nhật danh mục từ modal
			int categoryId = Integer.parseInt(req.getParameter("categoryId"));
			String categoryName = req.getParameter("categoryName");

			Category category = new Category();
			category.setCategory_id(categoryId);
			category.setName(categoryName);

			cateService.update(category);

			// Sau khi cập nhật, chuyển lại về trang danh sách
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
