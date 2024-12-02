package vn.iotstar.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.dao.implement.BlogDAO;
import vn.iotstar.entity.Blog;

@WebServlet("/blog")
public class BlogListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BlogDAO blogDAO;

    @Override
    public void init() throws ServletException {
        // Khởi tạo BlogDAO
        blogDAO = new BlogDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số
            String action = request.getParameter("action");
            String query = request.getParameter("query");
            String minDate = request.getParameter("minDate");
            String maxDate = request.getParameter("maxDate");

            // Kiểm tra trạng thái tìm kiếm
            boolean hasQuery = query != null && !query.trim().isEmpty();
            boolean hasDateRange = minDate != null && maxDate != null && isValidDateRange(minDate, maxDate);

            if (action == null || action.equals("list")) {
                if (!hasQuery && !hasDateRange) {
                    // Hiển thị danh sách nếu không có điều kiện tìm kiếm
                    handleListBlogs(request, response);
                } else if (hasQuery && hasDateRange) {
                    // Tìm kiếm theo từ khóa và ngày
                    handleSearchByQueryAndDate(request, response, query, minDate, maxDate);
                } else if (hasQuery) {
                    // Tìm kiếm chỉ theo từ khóa
                    handleSearchByQuery(request, response, query);
                } else if (hasDateRange) {
                    // Tìm kiếm chỉ theo ngày
                    handleSearchByDate(request, response, minDate, maxDate);
                } else {
                    // Xử lý lỗi nếu thông tin tìm kiếm không hợp lệ
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid search parameters");
                }
            } else if (action.equals("details")) {
                // Hiển thị chi tiết blog
                handleBlogDetails(request, response);
            } else {
                // Trả về lỗi nếu hành động không hợp lệ
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong");
        }
    }

    private void handleListBlogs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int blogsPerPage = 4; // Hiển thị 4 blog mỗi trang
        int currentPage = 1; // Trang mặc định là 1

        // Lấy tham số `page` từ URL nếu có
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán offset để lấy dữ liệu từ database
        int offset = (currentPage - 1) * blogsPerPage;

        // Lấy danh sách blog từ database theo phân trang
        List<Blog> blogs = blogDAO.getBlogsByPage(offset, blogsPerPage);
        int totalBlogs = blogDAO.getTotalBlogs(); // Tổng số blog
        int totalPages = (int) Math.ceil((double) totalBlogs / blogsPerPage); // Tính tổng số trang

        // Đặt các tham số vào request để gửi tới JSP
        request.setAttribute("blogs", blogs);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        // Chuyển tiếp tới blog.jsp
        request.getRequestDispatcher("/views/blog.jsp").forward(request, response);
    }

    private void handleBlogDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số `id` từ URL
            int blogId = Integer.parseInt(request.getParameter("id"));

            // Lấy thông tin blog từ database
            Blog blog = blogDAO.getBlogById(blogId);

            if (blog != null) {
                // Nếu blog tồn tại, đặt vào request attribute
                request.setAttribute("blog", blog);

                // Chuyển tiếp tới blog-details.jsp
                request.getRequestDispatcher("/views/blog-details.jsp").forward(request, response);
            } else {
                // Nếu không tìm thấy blog, trả về lỗi 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Blog not found");
            }
        } catch (NumberFormatException e) {
            // Nếu tham số `id` không hợp lệ, trả về lỗi 400
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid blog ID");
        }
    }

    private void handleSearchByQuery(HttpServletRequest request, HttpServletResponse response, String query)
            throws ServletException, IOException {
        int blogsPerPage = 4;
        int currentPage = 1;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (currentPage - 1) * blogsPerPage;
        List<Blog> blogs = blogDAO.getBlogsByQueryWithPagination(query, offset, blogsPerPage);
        int totalBlogs = blogDAO.getTotalBlogsByQuery(query);
        int totalPages = (int) Math.ceil((double) totalBlogs / blogsPerPage);

        request.setAttribute("blogs", blogs);
        request.setAttribute("query", query);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/views/blog.jsp").forward(request, response);
    }

    private void handleSearchByDate(HttpServletRequest request, HttpServletResponse response, String minDate, String maxDate)
            throws ServletException, IOException {
        int blogsPerPage = 4;
        int currentPage = 1;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (currentPage - 1) * blogsPerPage;
        List<Blog> blogs = blogDAO.getBlogsByDateRangeWithPagination(minDate, maxDate, offset, blogsPerPage);
        int totalBlogs = blogDAO.getTotalBlogsByDateRange(minDate, maxDate);
        int totalPages = (int) Math.ceil((double) totalBlogs / blogsPerPage);

        request.setAttribute("blogs", blogs);
        request.setAttribute("minDate", minDate);
        request.setAttribute("maxDate", maxDate);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/views/blog.jsp").forward(request, response);
    }

    private void handleSearchByQueryAndDate(HttpServletRequest request, HttpServletResponse response, String query,
            String minDate, String maxDate) throws ServletException, IOException {
        int blogsPerPage = 4;
        int currentPage = 1;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int offset = (currentPage - 1) * blogsPerPage;
        List<Blog> blogs = blogDAO.getBlogsByQueryAndDateWithPagination(query, minDate, maxDate, offset, blogsPerPage);
        int totalBlogs = blogDAO.getTotalBlogsByQueryAndDate(query, minDate, maxDate);
        int totalPages = (int) Math.ceil((double) totalBlogs / blogsPerPage);

        request.setAttribute("blogs", blogs);
        request.setAttribute("query", query);
        request.setAttribute("minDate", minDate);
        request.setAttribute("maxDate", maxDate);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/views/blog.jsp").forward(request, response);
    }

    private boolean isValidDateRange(String minDate, String maxDate) {
        try {
            LocalDate start = LocalDate.parse(minDate);
            LocalDate end = LocalDate.parse(maxDate);
            return !start.isAfter(end) && !end.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
