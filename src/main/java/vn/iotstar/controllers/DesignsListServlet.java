package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.dao.implement.DesignsDAO;
import vn.iotstar.entity.Designs;

@WebServlet("/designs")
public class DesignsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DesignsDAO designsDAO;

	@Override
	public void init() throws ServletException {
		// Khởi tạo DesignsDAO
		designsDAO = new DesignsDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy tham số từ yêu cầu
			String action = request.getParameter("action");
			String query = request.getParameter("query");
			String minDate = request.getParameter("minDate");
			String maxDate = request.getParameter("maxDate");
			String productName = request.getParameter("productName"); // Tên sản phẩm để tìm kiếm

			// Kiểm tra trạng thái tìm kiếm
			boolean hasQuery = query != null && !query.trim().isEmpty();
			boolean hasDateRange = minDate != null && maxDate != null && isValidDateRange(minDate, maxDate);
			boolean hasProductName = productName != null && !productName.trim().isEmpty();

			if (action == null || action.equals("list")) {
				if (!hasQuery && !hasDateRange && !hasProductName) {
					// Hiển thị danh sách nếu không có điều kiện tìm kiếm
					handleListDesigns(request, response);
				} else if (hasProductName && hasQuery && hasDateRange) {
					// Tìm kiếm theo tên sản phẩm, từ khóa và ngày
					handleSearchByProductAndQueryAndDate(request, response, productName, query, minDate, maxDate);
				} else if (hasProductName && hasQuery) {
					// Tìm kiếm theo tên sản phẩm và từ khóa
					handleSearchByProductAndQuery(request, response, productName, query);
				} else if (hasQuery && hasDateRange) {
					// Tìm kiếm theo từ khóa và ngày
					handleSearchByQueryAndDate(request, response, query, minDate, maxDate);
				} else if (hasProductName && hasDateRange && !hasQuery) {
	                // Tìm kiếm theo tên sản phẩm và phạm vi ngày
	                handleSearchByProductAndDate(request, response, productName, minDate, maxDate);
				} else if (hasProductName) {
					// Tìm kiếm theo tên sản phẩm
					handleSearchByProductName(request, response, productName);
				} else if (hasQuery) {
					// Tìm kiếm chỉ theo từ khóa
					handleSearchByQuery(request, response, query);
				} else if (hasDateRange) {
					// Tìm kiếm chỉ theo ngày
					handleSearchByDate(request, response, minDate, maxDate);
				} else {
					// Xử lý lỗi nếu thông tin tìm kiếm không hợp lệ
					request.setAttribute("error", "Thông tin tìm kiếm không hợp lệ.");
					handleListDesigns(request, response);
				}
			} else if (action.equals("details")) {
				// Hiển thị chi tiết thiết kế
				handleDesignDetails(request, response);
			} else {
				// Trả về lỗi nếu hành động không hợp lệ
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong");
		}
	}

	private void handleListDesigns(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int designsPerPage = 4; // Hiển thị 4 thiết kế mỗi trang
		int currentPage = 1; // Trang mặc định là 1

		// Lấy tham số `page` từ URL nếu có
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		// Tính toán offset để lấy dữ liệu từ database
		int offset = (currentPage - 1) * designsPerPage;

		// Lấy danh sách thiết kế từ database theo phân trang
		List<Designs> designs = designsDAO.getDesignsByPage(offset, designsPerPage);
		int totalDesigns = designsDAO.getTotalDesigns(); // Tổng số thiết kế
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage); // Tính tổng số trang

		// Đặt các tham số vào request để gửi tới JSP
		request.setAttribute("designs", designs);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

		// Chuyển tiếp tới designs.jsp
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private void handleDesignDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy tham số `id` từ URL
			int designId = Integer.parseInt(request.getParameter("id"));

			// Lấy thông tin thiết kế từ database
			Designs design = designsDAO.getDesignById(designId);

			if (design != null) {
				// Nếu thiết kế tồn tại, đặt vào request attribute
				request.setAttribute("design", design);

				// Chuyển tiếp tới design-details.jsp
				request.getRequestDispatcher("/views/admin/design-detail.jsp").forward(request, response);
			} else {
				// Nếu không tìm thấy thiết kế, trả về lỗi 404
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Design not found");
			}
		} catch (NumberFormatException e) {
			// Nếu tham số `id` không hợp lệ, trả về lỗi 400
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid design ID");
		}
	}

	private void handleSearchByQuery(HttpServletRequest request, HttpServletResponse response, String query)
			throws ServletException, IOException {
		int designsPerPage = 4; // Số lượng thiết kế mỗi trang
		int currentPage = 1;

		// Lấy trang hiện tại từ tham số URL
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (currentPage - 1) * designsPerPage;

		// Lấy danh sách thiết kế theo tìm kiếm từ khóa và phân trang
		List<Designs> designs = designsDAO.getDesignsByQueryWithPagination(query, offset, designsPerPage);
		int totalDesigns = designsDAO.getTotalDesignsByQuery(query);
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage);

		// Đặt các tham số vào request
		request.setAttribute("designs", designs);
		request.setAttribute("query", query);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

		// Chuyển tiếp tới JSP
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private void handleSearchByDate(HttpServletRequest request, HttpServletResponse response, String minDate,
			String maxDate) throws ServletException, IOException {
		int designsPerPage = 4; // Số lượng thiết kế mỗi trang
		int currentPage = 1;

		// Lấy trang hiện tại từ tham số URL
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (currentPage - 1) * designsPerPage;

		// Lấy danh sách thiết kế theo phạm vi ngày và phân trang
		List<Designs> designs = designsDAO.getDesignsByDateRangeWithPagination(minDate, maxDate, offset,
				designsPerPage);
		int totalDesigns = designsDAO.getTotalDesignsByDateRange(minDate, maxDate);
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage);

		// Đặt các tham số vào request
		request.setAttribute("designs", designs);
		request.setAttribute("minDate", minDate);
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

		// Chuyển tiếp tới JSP
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private void handleSearchByQueryAndDate(HttpServletRequest request, HttpServletResponse response, String query,
			String minDate, String maxDate) throws ServletException, IOException {
		int designsPerPage = 4; // Số lượng thiết kế mỗi trang
		int currentPage = 1;

		// Lấy trang hiện tại từ tham số URL
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (currentPage - 1) * designsPerPage;

		// Lấy danh sách thiết kế theo từ khóa, ngày và phân trang
		List<Designs> designs = designsDAO.getDesignsByQueryAndDateWithPagination(query, minDate, maxDate, offset,
				designsPerPage);
		int totalDesigns = designsDAO.getTotalDesignsByQueryAndDate(query, minDate, maxDate);
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage);

		// Đặt các tham số vào request
		request.setAttribute("designs", designs);
		request.setAttribute("query", query);
		request.setAttribute("minDate", minDate);
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

		// Chuyển tiếp tới JSP
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private void handleSearchByProductName(HttpServletRequest request, HttpServletResponse response, String productName)
			throws ServletException, IOException {
		int designsPerPage = 4; // Số lượng thiết kế mỗi trang
		int currentPage = 1; // Trang mặc định là 1

		// Lấy tham số `page` từ URL nếu có
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (currentPage - 1) * designsPerPage;

		// Gọi DAO để tìm kiếm theo tên sản phẩm
		List<Designs> designs = designsDAO.getDesignsByProductNameWithPagination(productName, offset, designsPerPage);
		int totalDesigns = designsDAO.getTotalDesignsByProductName(productName); // Tổng số thiết kế
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage); // Tính tổng số trang

		// Đặt các tham số vào request
		request.setAttribute("designs", designs);
		request.setAttribute("productName", productName);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

		// Chuyển tiếp tới designs.jsp
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private void handleSearchByProductAndQuery(HttpServletRequest request, HttpServletResponse response,
			String productName, String query) throws ServletException, IOException {
		int designsPerPage = 4; // Số lượng thiết kế mỗi trang
		int currentPage = 1; // Trang mặc định là 1

// Lấy trang hiện tại từ tham số URL
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (currentPage - 1) * designsPerPage;

// Gọi DAO để tìm kiếm theo tên sản phẩm và từ khóa
		List<Designs> designs = designsDAO.getDesignsByProductAndQueryWithPagination(productName, query, offset,
				designsPerPage);
		int totalDesigns = designsDAO.getTotalDesignsByProductAndQuery(productName, query);
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage);

// Đặt các tham số vào request
		request.setAttribute("designs", designs);
		request.setAttribute("productName", productName);
		request.setAttribute("query", query);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

// Chuyển tiếp tới designs.jsp
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private void handleSearchByProductAndQueryAndDate(HttpServletRequest request, HttpServletResponse response,
			String productName, String query, String minDate, String maxDate) throws ServletException, IOException {
		int designsPerPage = 4; // Số lượng thiết kế mỗi trang
		int currentPage = 1; // Trang mặc định là 1

// Lấy trang hiện tại từ tham số URL
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (currentPage - 1) * designsPerPage;

// Gọi DAO để tìm kiếm theo sản phẩm, từ khóa, và ngày
		List<Designs> designs = designsDAO.getDesignsByProductQueryAndDateWithPagination(productName, query, minDate,
				maxDate, offset, designsPerPage);
		int totalDesigns = designsDAO.getTotalDesignsByProductQueryAndDate(productName, query, minDate, maxDate);
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage);

// Đặt các tham số vào request
		request.setAttribute("designs", designs);
		request.setAttribute("productName", productName);
		request.setAttribute("query", query);
		request.setAttribute("minDate", minDate);
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

// Chuyển tiếp tới designs.jsp
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private void handleSearchByProductAndDate(HttpServletRequest request, HttpServletResponse response,
			String productName, String minDate, String maxDate) throws ServletException, IOException {
		int designsPerPage = 4; // Số lượng thiết kế mỗi trang
		int currentPage = 1; // Trang mặc định là 1

// Lấy tham số `page` từ URL nếu có
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int offset = (currentPage - 1) * designsPerPage;

// Gọi DAO để lấy danh sách thiết kế
		List<Designs> designs = designsDAO.getDesignsByProductAndDateWithPagination(productName, minDate, maxDate,
				offset, designsPerPage);
		int totalDesigns = designsDAO.getTotalDesignsByProductAndDate(productName, minDate, maxDate);
		int totalPages = (int) Math.ceil((double) totalDesigns / designsPerPage);

// Đặt các tham số vào request
		request.setAttribute("designs", designs);
		request.setAttribute("productName", productName);
		request.setAttribute("minDate", minDate);
		request.setAttribute("maxDate", maxDate);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);

// Chuyển tiếp tới designs.jsp
		request.getRequestDispatcher("/views/designs.jsp").forward(request, response);
	}

	private boolean isValidDateRange(String minDate, String maxDate) {
		try {
			LocalDate start = LocalDate.parse(minDate);
			LocalDate end = LocalDate.parse(maxDate);
			return !end.isAfter(LocalDate.now()) && !start.isAfter(end);
		} catch (DateTimeParseException e) {
			return false;
		}
	}

}
