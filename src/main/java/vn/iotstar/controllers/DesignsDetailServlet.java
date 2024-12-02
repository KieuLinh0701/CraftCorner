package vn.iotstar.controllers;

import vn.iotstar.dao.implement.DesignsDAO;
import vn.iotstar.entity.Designs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/designs-details")
public class DesignsDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DesignsDAO designsDAO;

    @Override
    public void init() {
        designsDAO = new DesignsDAO(); // Khởi tạo DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tham số design ID từ URL
            int designId = Integer.parseInt(request.getParameter("id"));
   
            // Truy vấn thiết kế từ cơ sở dữ liệu
            Designs design = designsDAO.getDesignById(designId);

            if (design != null) {
                // Đặt thiết kế vào request attribute
                request.setAttribute("design", design);
                System.out.println(design);
                // Chuyển tiếp đến design-details.jsp
                request.getRequestDispatcher("/views/designs-details.jsp").forward(request, response);
            } else {
                // Nếu không tìm thấy thiết kế, trả về lỗi 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Design not found");
            }
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu ID không hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Design ID");
        }
    }
}
