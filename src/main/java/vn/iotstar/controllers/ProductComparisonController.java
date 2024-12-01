package vn.iotstar.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import vn.iotstar.services.implement.ProductService;
import vn.iotstar.entity.Product;

import java.io.IOException;

@WebServlet(urlPatterns = { "/compare" })
public class ProductComparisonController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductService productService;

    @Override
    public void init() {
        // Khởi tạo ProductService nếu cần thiết
        productService = new ProductService(); 
    }

    // Xử lý yêu cầu GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId = Long.parseLong(request.getParameter("id"));  // Lấy id sản phẩm từ URL
        Product product = productService.getProductById(productId);
        
        request.setAttribute("product1", product);
        
        // Forward đến trang so sánh sản phẩm
        RequestDispatcher dispatcher = request.getRequestDispatcher("compareProducts.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xử lý yêu cầu POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId1 = Long.parseLong(request.getParameter("productId1"));
        Long productId2 = Long.parseLong(request.getParameter("productId2"));

        Product product1 = productService.getProductById(productId1);
        Product product2 = productService.getProductById(productId2);

        // Kiểm tra nếu sản phẩm thuộc cùng một loại
        if (!productService.isSameCategory(product1, product2)) {
            request.setAttribute("error", "Sản phẩm không cùng loại");
            request.setAttribute("product1", product1);
            request.setAttribute("product2", product2);
            
            // Forward lại trang so sánh sản phẩm với thông báo lỗi
            RequestDispatcher dispatcher = request.getRequestDispatcher("compareProducts.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        // Chuyển tiếp thông tin sản phẩm lên JSP
        request.setAttribute("product1", product1);
        request.setAttribute("product2", product2);
        
        // Forward đến trang so sánh sản phẩm
        RequestDispatcher dispatcher = request.getRequestDispatcher("compareProducts.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
