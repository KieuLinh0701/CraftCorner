package vn.iotstar.controllers.admin;

import vn.iotstar.services.IOrderService;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String periodType = request.getParameter("periodType");
        String periodValue = request.getParameter("periodValue");

        // Kiểm tra periodType và periodValue
        if (periodType != null && periodValue != null) {
            // Lấy dữ liệu từ Service
            Long totalOrders = orderService.getTotalOrders(periodType, periodValue);
            Double totalRevenue = orderService.getTotalRevenue(periodType, periodValue);
            String highestRevenueProduct = orderService.getHighestRevenueProduct(periodType, periodValue);
            String lowestRevenueProduct = orderService.getLowestRevenueProduct(periodType, periodValue);
            String mostPurchasedProduct = orderService.getMostPurchasedProduct(periodType, periodValue);
            String leastPurchasedProduct = orderService.getLeastPurchasedProduct(periodType, periodValue);

            // Set vào request attributes để sử dụng trong JSP
            request.setAttribute("totalOrders", totalOrders);
            request.setAttribute("totalRevenue", totalRevenue);
            request.setAttribute("highestRevenueProduct", highestRevenueProduct);
            request.setAttribute("lowestRevenueProduct", lowestRevenueProduct);
            request.setAttribute("mostPurchasedProduct", mostPurchasedProduct);
            request.setAttribute("leastPurchasedProduct", leastPurchasedProduct);
        }

        // Chuyển hướng tới trang JSP
        request.getRequestDispatcher("/admin/revenue.jsp").forward(request, response);
    }
}
