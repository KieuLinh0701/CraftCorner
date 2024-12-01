package vn.iotstar.controllers.admin;

import static vn.iotstar.utils.Constant.UPLOAD_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.DesignItem;
import vn.iotstar.entity.Designs;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.User;
import vn.iotstar.services.IDesignItemService;
import vn.iotstar.services.IDesignService;
import vn.iotstar.services.IProductService;
import vn.iotstar.services.implement.DesignItemService;
import vn.iotstar.services.implement.DesignService;
import vn.iotstar.services.implement.ProductService;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/designs", "/admin/design/designdetail", "/admin/designs/search", "/admin/design/add",
		"/admin/design/edit", "/admin/design/update", "/admin/design/insert", "/admin/designitems",
		"/admin/designitems/search", "/admin/designitem/insert", "/admin/designitem/delete",
		"/admin/design/deletestep2", "/admin/design/delete" })
public class DesignController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public IDesignService designService = new DesignService();
	public IDesignItemService itemService = new DesignItemService();
	public IProductService productService = new ProductService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("designitem/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			itemService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/designitems");
		} else if (url.contains("designitem/insert")) {
			DesignItem item = new DesignItem();
			HttpSession session = req.getSession();
			Designs design = (Designs) session.getAttribute("newDesign");
			item.setDesign(design);

			int id = Integer.parseInt(req.getParameter("id"));
			Product product = productService.findById(id);
			item.setProduct(product);

			itemService.insert(item);
			resp.sendRedirect(req.getContextPath() + "/admin/designitems");
		} else if (url.contains("designitems/search")) {
			HttpSession session = req.getSession();
			Designs design = (Designs) session.getAttribute("newDesign");
			int designId = design.getDesignId();
			req.setAttribute("designId", designId);

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

			Designs des = designService.findById(designId);
			Set<DesignItem> listItem = des.getDesignItems();
			req.setAttribute("listItem", listItem);

			req.setAttribute("totalPages", totalPages);
			req.setAttribute("page", page);
			req.setAttribute("search", keyword);
			req.getRequestDispatcher(Constant.DESIGN_ADD_STEP2).forward(req, resp);
		} else if (url.contains("designs/search")) {
			String keyword = req.getParameter("search");

			String pageStr = req.getParameter("page");
			int page;
			if (pageStr == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageStr);
			}
			int totalPages = designService.countDesign(Constant.PAGESIZE, keyword);

			List<Designs> listDesign = designService.findByTitle(page, Constant.PAGESIZE, keyword);
			if (listDesign.size() == 0) {
				req.setAttribute("alert", "No design templates are currently available for display");
			} else {
				req.setAttribute("listDesign", listDesign);
			}
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("page", page);
			req.setAttribute("search", keyword);
			req.getRequestDispatcher(Constant.DESIGN_MANAGEMENT).forward(req, resp);
		} else if (url.contains("designs")) {
			String pageStr = req.getParameter("page");
			int page;
			if (pageStr == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageStr);
			}
			int totalPages = designService.countDesign(Constant.PAGESIZE);

			List<Designs> listDesign = designService.findAll(page, Constant.PAGESIZE);
			if (listDesign.size() == 0) {
				req.setAttribute("alert", "No design templates are currently available for display");
			} else {
				req.setAttribute("listDesign", listDesign);
			}
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("page", page);
			req.getRequestDispatcher(Constant.DESIGN_MANAGEMENT).forward(req, resp);
		} else if (url.contains("design/add")) {
			req.getRequestDispatcher(Constant.DESIGN_ADD_STEP1).forward(req, resp);
		} else if (url.contains("designitems")) {
			HttpSession session = req.getSession();
			Designs design = (Designs) session.getAttribute("newDesign");
			int designId = design.getDesignId();
			req.setAttribute("designId", designId);

			Designs des = designService.findById(designId);
			Set<DesignItem> listItem = des.getDesignItems();
			req.setAttribute("listItem", listItem);

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
			req.getRequestDispatcher(Constant.DESIGN_ADD_STEP2).forward(req, resp);
		} else if (url.contains("design/deletestep2")) {
			Designs design = (Designs) req.getSession().getAttribute("newDesign");
			HttpSession session = req.getSession();
			session.removeAttribute("newDesign");
			int id = design.getDesignId();

			List<DesignItem> listItem = itemService.findByIdDesign(id);
			for (DesignItem x : listItem) {
				itemService.delete(x.getDesignItem_id());
			}

			designService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/designs");
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));

			List<DesignItem> listItem = itemService.findByIdDesign(id);
			for (DesignItem x : listItem) {
				itemService.delete(x.getDesignItem_id());
			}

			designService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/admin/designs");
		} else if (url.contains("edit")) {
			int designId = Integer.parseInt(req.getParameter("id"));
			Designs design = designService.findById(designId);
			req.setAttribute("design", design);
			req.getRequestDispatcher(Constant.DESIGN_EDIT_STEP1).forward(req, resp);
		} else if (url.contains("designdetail")) {
			int designId = Integer.parseInt(req.getParameter("id"));
			Designs design = designService.findById(designId);
			req.setAttribute("design", design);
			req.getRequestDispatcher(Constant.DESIGN_DETAIL_MANAGEMENT).forward(req, resp);
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("designitems/search")) {
			HttpSession session = req.getSession();
			Designs design = (Designs) session.getAttribute("newDesign");
			int designId = design.getDesignId();
			req.setAttribute("designId", designId);

			Designs des = designService.findById(designId);
			Set<DesignItem> listItem = des.getDesignItems();
			req.setAttribute("listItem", listItem);

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
			req.getRequestDispatcher(Constant.DESIGN_ADD_STEP2).forward(req, resp);
		} else if (url.contains("designs/search")) {
			String keyword = req.getParameter("search");

			String pageStr = req.getParameter("page");
			int page;
			if (pageStr == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageStr);
			}
			int totalPages = designService.countDesign(Constant.PAGESIZE, keyword);

			List<Designs> listDesign = designService.findByTitle(page, Constant.PAGESIZE, keyword);
			if (listDesign.size() == 0) {
				req.setAttribute("alert", "No design templates are currently available for display");
			} else {
				req.setAttribute("listDesign", listDesign);
			}
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("page", page);
			req.setAttribute("search", keyword);
			req.getRequestDispatcher(Constant.DESIGN_MANAGEMENT).forward(req, resp);
		} else if (url.contains("design/insert")) {
			Designs design = new Designs();

			String title = req.getParameter("title");
			design.setTitle(title);

			String content = req.getParameter("content");
			design.setContent(content);

			design.setCreateDate(LocalDate.now());

			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			design.setUser(user);

			String status = req.getParameter("status");
			design.setStatus(status != null ? 1 : 0);

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
					design.setImage(fname);
				} else {
					design.setImage("Image_error.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			Designs newDesign = designService.insert(design);
			req.getSession().setAttribute("newDesign", newDesign);
			resp.sendRedirect(req.getContextPath() + "/admin/designitems");
		} else if (url.contains("design/update")) {
			Designs newDes = new Designs();
			
			int id = Integer.parseInt(req.getParameter("designId"));
			newDes.setDesignId(id);
			
			String title = req.getParameter("title");
			newDes.setTitle(title);

			String content = req.getParameter("content");
			newDes.setContent(content);

			String status = req.getParameter("status");
			newDes.setStatus(status != null ? 1 : 0);

			// Lưu hình ảnh cũ
			Designs design = designService.findById(id);
			newDes.setCreateDate(design.getCreateDate());
			newDes.setUser(design.getUser());
			
			String fileOld = design.getImage();

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
					newDes.setImage(fname);
				} else {
					newDes.setImage(fileOld);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			designService.update(newDes);
			req.getSession().setAttribute("newDesign", newDes);
			resp.sendRedirect(req.getContextPath() + "/admin/designitems");
		}
	}

}
