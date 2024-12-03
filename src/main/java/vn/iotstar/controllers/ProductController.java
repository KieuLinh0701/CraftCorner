package vn.iotstar.controllers;

import vn.iotstar.services.IProductService;
import vn.iotstar.services.implement.ProductService;
import vn.iotstar.utils.Constant;
import vn.iotstar.entity.Product;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/product-details" })
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the product ID from the request parameter
        String productIdParam = req.getParameter("id");

        if (productIdParam != null) {
            try {
                int productId = Integer.parseInt(productIdParam); // Convert the ID to an integer
                // Fetch the product from the service by ID
                Product product = productService.getProductById(productId);

                // Set the product as a request attribute
                req.setAttribute("product", product);

                // Forward the request to the JSP page for displaying the product
                req.getRequestDispatcher(Constant.PRODUCTS).forward(req, resp);
            } catch (NumberFormatException e) {
                // Handle invalid ID format
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
            }
        } else {
            // Handle case where no ID is provided
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
        }
    }
}
