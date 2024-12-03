package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDao implements ICategoryDao{
	public Category findByName(String name) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.createQuery(
                "SELECT c FROM Category c WHERE c.name = :name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu không tìm thấy
        } finally {
            enma.close();
        }
    }

    public void save(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(category);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e; // Ném lại exception để xử lý ở lớp service nếu cần
        } finally {
            enma.close();
        }
    }

    public List<Category> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.createQuery("SELECT c FROM Category c", Category.class)
                       .getResultList();
        } finally {
            enma.close();
        }
    }
}
