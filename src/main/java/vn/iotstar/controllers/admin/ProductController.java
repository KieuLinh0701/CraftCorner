package vn.iotstar.controllers.admin;

import static vn.iotstar.utils.Constant.UPLOAD_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Designs;
import vn.iotstar.entity.Product;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IProductService;
import vn.iotstar.services.implement.CategoryService;
import vn.iotstar.services.implement.ProductService;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/products", "/admin/products/search", "/admin/product/add", "/admin/product/insert",
		"/admin/product/edit", "/admin/product/update", "/admin/product/delete"})
public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public IProductService productService = new ProductService();
	public ICategoryService categoryService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("products/search")) {
			String keyword = req.getParameter("search");

			String pageStr = req.getParameter("page");
			int page;
			if (pageStr == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageStr);
			}
			int totalPages = productService.countProduct(Constant.PAGESIZE, keyword);

			List<Product> listProduct = productService.findByName(page, Constant.PAGESIZE, keyword);
			if (listProduct.size() == 0) {
				req.setAttribute("alert", "No design templates are currently available for display");
			} else {
				req.setAttribute("listProduct", listProduct);
			}
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("page", page);
			req.setAttribute("search", keyword);
			req.getRequestDispatcher(Constant.PRODUCT_MANAGEMENT).forward(req, resp);
		} else if (url.contains("products")) {
			String pageStr = req.getParameter("page");
			int page;
			if (pageStr == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageStr);
			}
			int totalPages = productService.countProduct(Constant.PAGESIZE);

			List<Product> listProduct = productService.findAll(page, Constant.PAGESIZE);
			if (listProduct.size() == 0) {
				req.setAttribute("alert", "No design templates are currently available for display");
			} else {
				req.setAttribute("listProduct", listProduct);
			}
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("page", page);
			req.getRequestDispatcher(Constant.PRODUCT_MANAGEMENT).forward(req, resp);
		} else if (url.contains("add")) {
			List<Category> listCategory = categoryService.findAll();
			req.setAttribute("listCategory", listCategory);
			req.getRequestDispatcher(Constant.PRODUCT_ADD).forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Product product = productService.findById(id);
			req.setAttribute("product", product);
			List<Category> listCategory = categoryService.findAll();
			req.setAttribute("listCategory", listCategory);
			req.getRequestDispatcher(Constant.PRODUCT_EDIT).forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				productService.delete(id);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/products");
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("products/search")) {
			String keyword = req.getParameter("search");

			String pageStr = req.getParameter("page");
			int page;
			if (pageStr == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageStr);
			}
			int totalPages = productService.countProduct(Constant.PAGESIZE, keyword);

			List<Product> ListProduct = productService.findByName(page, Constant.PAGESIZE, keyword);
			if (ListProduct.size() == 0) {
				req.setAttribute("alert", "No design templates are currently available for display");
			} else {
				req.setAttribute("listProduct", ListProduct);
			}
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("page", page);
			req.setAttribute("search", keyword);
			req.getRequestDispatcher(Constant.PRODUCT_MANAGEMENT).forward(req, resp);
		} else if (url.contains("insert")) {

			Product product = new Product();

			String color = req.getParameter("color");
			product.setColor(color);

			String status = req.getParameter("status");
			product.setStatus(status != null ? 1 : 0);

			product.setCreateDate(LocalDateTime.now());

			String name = req.getParameter("name");
			product.setName(name);

			String material = req.getParameter("material");
			product.setMaterial(material);

			Float height = Float.parseFloat(req.getParameter("height"));
			product.setHeight(height);
			
			Float length = Float.parseFloat(req.getParameter("length"));
			product.setLength(length);
			
			Float width = Float.parseFloat(req.getParameter("width"));
			product.setWidth(width);

			int price = Integer.parseInt(req.getParameter("price"));
			product.setPrice(price);

			int quantity = Integer.parseInt(req.getParameter("quantity"));
			product.setQuantity(quantity);

			String description = req.getParameter("description");
			product.setDescription(description);

			String categoryId = req.getParameter("category");
			int category_id = Integer.parseInt(categoryId);
			Category category = categoryService.findById(category_id);
			product.setCategory(category);

			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload File
					part.write(uploadPath + File.separator + fname);
					// Ghi tên file vào data
					product.setImage(fname);
				} else {
					product.setImage("Image_error.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			productService.insert(product);
			resp.sendRedirect(req.getContextPath() + "/admin/products");
		} else if (url.contains("update")) {
			Product product = new Product();
			
			int product_id = Integer.parseInt(req.getParameter("product_id"));
			product.setProduct_id(product_id);

			String color = req.getParameter("color");
			product.setColor(color);

			String status = req.getParameter("status");
			product.setStatus(status != null ? 1 : 0);

			product.setCreateDate(LocalDateTime.now());

			String name = req.getParameter("name");
			product.setName(name);

			String material = req.getParameter("material");
			product.setMaterial(material);

			String height = req.getParameter("height");
			if (height != null) {
				product.setHeight(Float.parseFloat(height));
			}

			String length = req.getParameter("length");
			if (length != null) {
				product.setLength(Float.parseFloat(length));
			}

			String width = req.getParameter("width");
			if (width != null) {
				product.setWidth(Float.parseFloat(width));
			}

			int price = Integer.parseInt(req.getParameter("price"));
			product.setPrice(price);

			int quantity = Integer.parseInt(req.getParameter("quantity"));
			product.setQuantity(quantity);

			String description = req.getParameter("description");
			product.setDescription(description);

			String categoryId = req.getParameter("category");
			int category_id = Integer.parseInt(categoryId);
			Category category = categoryService.findById(category_id);
			product.setCategory(category);

			// Lưu hình ảnh cũ
			Product oldPro = productService.findById(product_id);
			String fileOld = oldPro.getImage();

			// Xử lý images
			String fname = "";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// Đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload File
					part.write(uploadPath + File.separator + fname);
					// Ghi tên file vào data
					product.setImage(fname);
				} else {
					product.setImage(fileOld);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			productService.update(product);
			resp.sendRedirect(req.getContextPath() + "/admin/products");
		}
	}

}
