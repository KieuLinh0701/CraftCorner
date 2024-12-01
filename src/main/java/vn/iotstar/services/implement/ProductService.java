package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IProductDao;
import vn.iotstar.dao.implement.ProductDao;
import vn.iotstar.entity.Product;
import vn.iotstar.services.IProductService;

public class ProductService  implements IProductService {

	private IProductDao warehouse = new ProductDao();

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


    public boolean isSameCategory(Product p1, Product p2) {
        return p1.getCategory().equals(p2.getCategory());
    }

    public List<Product> findProductsByCategory(String category) {
        return warehouse.searchProductsByName(category);
    }
}
