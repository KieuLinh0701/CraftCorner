package vn.iotstar.controllers.admin;

import vn.iotstar.services.IOrderService;
import vn.iotstar.services.implement.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/revenue")
public class RevenueController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IOrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = new OrderService(); // Khởi tạo service ở phương thức init
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String periodType = request.getParameter("periodType");
        String periodValue = request.getParameter("periodValue");

        // Kiểm tra periodType và periodValue và khởi tạo giá trị mặc định
        if (periodType == null) {
            periodType = "Select Date"; // Giá trị mặc định nếu không có tham số
        }
        if (periodValue == null) {
            periodValue = java.time.LocalDate.now().toString(); // Mặc định là ngày hiện tại
        }

        // Lấy dữ liệu từ Service
        Long totalOrders = orderService.getTotalOrders(periodType, periodValue);
        Double totalRevenue = orderService.getTotalRevenue(periodType, periodValue);
        String highestRevenueProduct = orderService.getHighestRevenueProduct(periodType, periodValue);
        String lowestRevenueProduct = orderService.getLowestRevenueProduct(periodType, periodValue);
        String mostPurchasedProduct = orderService.getMostPurchasedProduct(periodType, periodValue);
        String leastPurchasedProduct = orderService.getLeastPurchasedProduct(periodType, periodValue);

        // Đặt các thuộc tính vào request để sử dụng trong JSP
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("highestRevenueProduct", highestRevenueProduct);
        request.setAttribute("lowestRevenueProduct", lowestRevenueProduct);
        request.setAttribute("mostPurchasedProduct", mostPurchasedProduct);
        request.setAttribute("leastPurchasedProduct", leastPurchasedProduct);
        request.setAttribute("selectedPeriod", periodType); // Gửi giá trị periodType vào JSP
        request.setAttribute("selectedDate", periodValue); // Gửi giá trị periodValue vào JSP

        // Chuyển hướng tới trang JSP
        request.getRequestDispatcher("/views/admin/revenue.jsp").forward(request, response);
    }
}
