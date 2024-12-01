package vn.iotstar.controllers.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Product;
import vn.iotstar.services.IProductService;

@WebServlet("/warehouse")
public class WarehouseController extends HttpServlet	{
	private static final long serialVersionUID = 1L;
	private IProductService WarehouseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", WarehouseService.getAllProducts());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/productList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/productForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Product existingProduct = WarehouseService.getProductById(id);
        request.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/productForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("")) {
            action = "insert";
        }

        switch (action) {
            case "insert":
                insertProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                response.sendRedirect("products");
                break;
        }
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setPrice(Integer.parseInt(request.getParameter("price")));
        product.setImage(request.getParameter("image"));
        product.setColor(request.getParameter("color"));
        product.setMaterial(request.getParameter("material"));
        product.setHeight(Integer.parseInt(request.getParameter("height")));
        product.setLength(Integer.parseInt(request.getParameter("length")));
        product.setWidth(Integer.parseInt(request.getParameter("width")));
        product.setCategory(request.getParameter("category"));

        WarehouseService.addProduct(product);
        response.sendRedirect("products");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Product product = WarehouseService.getProductById(id);

        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setPrice(Integer.parseInt(request.getParameter("price")));
        product.setImage(request.getParameter("image"));
        product.setColor(request.getParameter("color"));
        product.setMaterial(request.getParameter("material"));
        product.setHeight(Integer.parseInt(request.getParameter("height")));
        product.setLength(Integer.parseInt(request.getParameter("length")));
        product.setWidth(Integer.parseInt(request.getParameter("width")));
        product.setCategory(request.getParameter("category"));

        WarehouseService.updateProduct(product);
        response.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        WarehouseService.deleteProduct(id);
        response.sendRedirect("products");
    }
}
