package vn.iotstar.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICartDao;
import vn.iotstar.entity.Cart;

public class CartDao implements ICartDao{

	@Override
	public Cart findByUser(int user_id) {
		 EntityManager enma = JPAConfig.getEntityManager();
	        String jpql = "SELECT c FROM Cart c WHERE c.user.id = :id";
	        TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);
	        query.setParameter("id", user_id);
	        try {
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	}

}
