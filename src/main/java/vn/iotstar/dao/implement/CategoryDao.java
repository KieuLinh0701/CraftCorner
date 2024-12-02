package vn.iotstar.dao.implement;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IPaymentDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.entity.User;

public class CategoryDao implements ICategoryDao {

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public Category findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class, id);
		return category;
	}

	@Override
	public void insert(Category cate) {
		EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(cate);
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
	public void delete(int category_id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category cate = enma.find(Category.class, category_id);
			if (cate != null) {
				enma.remove(cate);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			
		} finally {
			enma.close();
		}		
	}

	@Override
	public void update(Category cate) {
		EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(cate);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }		
	}
}
