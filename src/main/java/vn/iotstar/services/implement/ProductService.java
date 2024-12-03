package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IProductDao;
import vn.iotstar.dao.implement.ProductDao;
import vn.iotstar.entity.Product;
import vn.iotstar.services.IProductService;

public class ProductService implements IProductService {

    private IProductDao warehouse;


    public ProductService() {
        // Khởi tạo warehouse (IProductDao) ở đây, có thể qua một framework DI hoặc khởi tạo thủ công
        this.warehouse = new ProductDao(); // Ví dụ: khởi tạo thủ công
    }

    @Override
    public List<Product> getAllProducts() {
        return warehouse.getAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return warehouse.getProductById(id);
    }

    @Override
    public void addProduct(Product product) {
        warehouse.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        warehouse.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        warehouse.deleteProduct(id);
    }
    
    @Override
    public boolean updateProductQuantity(long productId, int quantity) {
        return warehouse.updateProductQuantity(productId, quantity);
    }

    
    @Override
    public boolean isSameCategory(Product p1, Product p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.getCategory().equals(p2.getCategory());
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        return warehouse.findProductsByCategory(category);
    }
}
