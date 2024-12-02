package vn.iotstar.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IProductDao;
import vn.iotstar.entity.Product;
import java.util.List;

public class ProductDao implements IProductDao {
    
    @Override
    public List<Product> getAllProducts() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            TypedQuery<Product> query = entityManager.createNamedQuery("Product.findAll", Product.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    public List<Product> getNewArrivals() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p WHERE p.status = :status ORDER BY p.createDate DESC", Product.class)
                                              .setParameter("status", 1) // assuming status = 1 means available
                                              .setMaxResults(5) // limit to 5 new arrivals
                                              .getResultList();
        return products;
    }
    
    @Override
    public Product getProductById(int productId) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        try {
            return entityManager.find(Product.class, productId);  // Finds the product by primary key
        } finally {
            entityManager.close();
        }
    }
}