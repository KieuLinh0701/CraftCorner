package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vn.iotstar.dao.IProductDao;

import vn.iotstar.entity.Product;

public class ProductDao implements IProductDao {

	@PersistenceContext
    private EntityManager entityManager;

    @Override	
    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", Product.class)
                            .setParameter("name", "%" + name + "%")
                            .getResultList();
    }

    @Override
    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice", Product.class)
                            .setParameter("minPrice", minPrice)
                            .setParameter("maxPrice", maxPrice)
                            .getResultList();
    }

    @Override
    public List<Product> searchProductsByQuantityRange(int minQuantity, int maxQuantity) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.quantity BETWEEN :minQuantity AND :maxQuantity", Product.class)
                            .setParameter("minQuantity", minQuantity)
                            .setParameter("maxQuantity", maxQuantity)
                            .getResultList();
    }

    @Override
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
 // Thêm phương thức tìm kiếm sản phẩm cùng loại
    @Override
    public List<Product> findProductsByCategory(String category) {
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class)
                            .setParameter("category", category)
                            .getResultList();
    }
}
