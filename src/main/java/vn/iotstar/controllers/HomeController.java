package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import vn.iotstar.services.IProductService;
import vn.iotstar.services.implement.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.utils.Constant;
import vn.iotstar.entity.Product;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // ProductService to fetch products from database
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch the product list from the database
        List<Product> products = productService.getAllProducts();

        // Set the products as a request attribute so it can be accessed in JSP
        req.setAttribute("products", products);

        // Forward the request to the homepage JSP
        req.getRequestDispatcher(Constant.HOME).forward(req, resp);
    }
}
