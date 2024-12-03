package vn.iotstar.controllers.admin;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Product;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IProductService;
import vn.iotstar.services.implement.CategoryService;
import vn.iotstar.services.implement.ProductService;

@WebServlet(urlPatterns = {"/admin/warehouse", "/admin/AddProduct.jsp" })
public class WarehouseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductService productService;
    private ICategoryService categoryService;

    @Override
    public void init() throws ServletException {
        // Tiêm dịch vụ cần thiết cho Controller
        productService = new ProductService();
        categoryService = new CategoryService();
    }

    // Phương thức kiểm tra và tạo Category nếu không tồn tại
    private Category getOrCreateCategory(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            category = new Category();
            category.setName(categoryName);
            categoryService.addCategory(category);
        }
        return category;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";  // Mặc định sẽ là hiển thị danh sách
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

    // Hiển thị danh sách sản phẩm
    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productService.getAllProducts());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/warehouse.jsp");
        dispatcher.forward(request, response);
    }

    // Hiển thị form để thêm sản phẩm mới
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/warehouse.jsp");
        dispatcher.forward(request, response);
    }

    // Hiển thị form để chỉnh sửa thông tin sản phẩm
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Product existingProduct = productService.getProductById(id);
        request.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/warehouse.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "insert";  // Mặc định hành động là insert
        }

        switch (action) {
            case "insert":
                insertProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "updatequantity":
            	updateProductQuantity(request, response);
	            break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                response.sendRedirect("products");
                break;
        }
    }

    private void updateProductQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long productId = Long.parseLong(request.getParameter("id"));
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        // Lấy sản phẩm từ ID và cập nhật số lượng
        Product product = productService.getProductById(productId);
        if (product != null) {
            product.setQuantity(newQuantity);  // Cập nhật số lượng
            productService.updateProductQuantity(productId, newQuantity);  // Cập nhật vào cơ sở dữ liệu
        }

        response.setStatus(HttpServletResponse.SC_OK);  // Trả về mã trạng thái 200 OK
    }


    // Thêm mới sản phẩm
    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setCategory(getOrCreateCategory(request.getParameter("category")));  // Gán category từ tên nhập vào
        setProductAttributes(request, product);  // Set các thuộc tính cho sản phẩm
        productService.addProduct(product);
        response.sendRedirect(request.getContextPath() + "/admin/warehouse");
    }

    // Cập nhật thông tin sản phẩm
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Product product = productService.getProductById(id);
        product.setCategory(getOrCreateCategory(request.getParameter("category")));  // Gán lại category
        setProductAttributes(request, product);  // Set lại các thuộc tính
        productService.updateProduct(product);
        response.sendRedirect(request.getContextPath() + "/admin/warehouse");
    }

    // Thiết lập các thuộc tính cho sản phẩm
    public void setProductAttributes(HttpServletRequest request, Product product ) {
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        
        // Kiểm tra và chuyển đổi các giá trị thành số, nếu không có giá trị thì đặt mặc định là 0
        String quantityStr = request.getParameter("quantity");
        if (quantityStr != null && !quantityStr.isEmpty()) {
            product.setQuantity(Integer.parseInt(quantityStr));
        } else {
            product.setQuantity(0); // Giá trị mặc định nếu không có giá trị
        }

        String priceStr = request.getParameter("price");
        if (priceStr != null && !priceStr.isEmpty()) {
            product.setPrice(Integer.parseInt(priceStr));
        } else {
            product.setPrice(0); // Giá trị mặc định nếu không có giá trị
        }

        product.setImage(request.getParameter("image"));
        product.setColor(request.getParameter("color"));
        product.setMaterial(request.getParameter("material"));
        
        // Kiểm tra và chuyển đổi các giá trị chiều cao, chiều dài, chiều rộng
        String heightStr = request.getParameter("height");
        if (heightStr != null && !heightStr.isEmpty()) {
            product.setHeight(Integer.parseInt(heightStr));
        } else {
            product.setHeight(0); // Giá trị mặc định nếu không có giá trị
        }

        String lengthStr = request.getParameter("length");
        if (lengthStr != null && !lengthStr.isEmpty()) {
            product.setLength(Integer.parseInt(lengthStr));
        } else {
            product.setLength(0); // Giá trị mặc định nếu không có giá trị
        }

        String widthStr = request.getParameter("width");
        if (widthStr != null && !widthStr.isEmpty()) {
            product.setWidth(Integer.parseInt(widthStr));
        } else {
            product.setWidth(0); // Giá trị mặc định nếu không có giá trị
        }
    }


    // Xóa sản phẩm
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect(request.getContextPath() + "/admin/warehouse");
    }
}
