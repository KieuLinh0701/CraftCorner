package vn.iotstar.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IProductDao;
import vn.iotstar.entity.Product;
import java.util.List;

public class ProductDao implements IProductDao {


    @Override
    public List<Product> getAllProducts() {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            List<Product> products = enma.createQuery("SELECT p FROM Product p", Product.class).getResultList();
            trans.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Product getProductById(Long id) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Product product = enma.find(Product.class, id);
            trans.commit();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            List<Product> products = enma.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name", Product.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
            trans.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            List<Product> products = enma.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice", Product.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
            trans.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Product> searchProductsByQuantityRange(int minQuantity, int maxQuantity) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            List<Product> products = enma.createQuery("SELECT p FROM Product p WHERE p.quantity BETWEEN :minQuantity AND :maxQuantity", Product.class)
                .setParameter("minQuantity", minQuantity)
                .setParameter("maxQuantity", maxQuantity)
                .getResultList();
            trans.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void addProduct(Product product) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void updateProduct(Product product) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(product);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Product product = enma.find(Product.class, id);
            if (product != null) {
                enma.remove(product);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Product> findProductsByCategory(String category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            List<Product> products = enma.createQuery("SELECT p FROM Product p WHERE p.category.name = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
            trans.commit();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }
    
    public boolean updateProductQuantity(long productId, int quantity) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();

            // Tìm sản phẩm theo productId
            Product product = enma.find(Product.class, productId);
            
            if (product == null) {
                return false; // Trả về false nếu không tìm thấy sản phẩm
            }

            // Cập nhật số lượng sản phẩm
            product.setQuantity(quantity);

            // Tiến hành merge (cập nhật) sản phẩm vào cơ sở dữ liệu
            enma.merge(product);

            trans.commit();
            return true; // Cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback(); // Hoàn tác giao dịch nếu có lỗi
            return false;
        } finally {
            enma.close(); // Đảm bảo đóng EntityManager
        }
    }


}
