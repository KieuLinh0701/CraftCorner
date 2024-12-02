package vn.iotstar.services.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IProductDao;
import vn.iotstar.dao.implement.ProductDao;
import vn.iotstar.entity.Product;
import vn.iotstar.services.IProductService;

public class ProductService implements IProductService {
    
    private IProductDao productDao;
    
    public ProductService() {
        this.productDao = new ProductDao();
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
    
    
    public Product getProductById(int productId) {
        // Logic to fetch the product by ID, for example, using JPA or JDBC
        // Assuming the use of JPA:
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Product.class, productId);
    }

}
