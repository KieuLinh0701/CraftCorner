package vn.iotstar.dao.implement;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
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
}
