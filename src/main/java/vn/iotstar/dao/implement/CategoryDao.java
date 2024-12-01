package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDao implements ICategoryDao {

	@Override
	public List<Category> findAll() {
		 EntityManager enma = JPAConfig.getEntityManager();
	        String sql = "SELECT c FROM Category c";
	        TypedQuery<Category> query = enma.createQuery(sql, Category.class);
	        return query.getResultList();
	}

}
