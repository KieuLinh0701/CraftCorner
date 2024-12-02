package vn.iotstar.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IOrderDao;
import vn.iotstar.entity.Order;

public class OrderDao implements IOrderDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Order insert(Order order) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(order);
			Order newOrder = enma.merge(order);
			trans.commit();
			return newOrder;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Order> findOrdersByUserId(int userId) {
		String query = "SELECT o FROM Order o WHERE o.user.id = :userId";
		return entityManager.createQuery(query, Order.class).setParameter("userId", userId).getResultList();
	}

	@Override
	public Order findOrderById(int orderId) {
		return entityManager.find(Order.class, orderId);

	}

	@Override
	public void updateOrder(Order order) {
		entityManager.merge(order);

	}

}
