package vn.iotstar.dao.implement;

import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICartItemDao;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.User;

public class CartItemDao implements ICartItemDao{

	@Override
	public List<CartItem> findByCartId(int cart_id) {
		EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM CartItem c WHERE c.cart.cart_id = :cart_id";
        TypedQuery<CartItem> query = enma.createQuery(jpql, CartItem.class);
        query.setParameter("cart_id", cart_id);
        return query.getResultList();
	}

	@Override
	public int totalPrice(Set<CartItem> listCartItem) {
		int total = 0;
		for (CartItem x : listCartItem) {
			 total = total + x.getQuantity()*x.getProduct().getPrice();
		}
		return total;
	}

	@Override
	public void delete(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			CartItem user = enma.find(CartItem.class, id);
			if (user != null) {
				enma.remove(user);
			} else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(user);
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
	public CartItem findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		CartItem cartItem = enma.find(CartItem.class, id);
		return cartItem;
	}

	@Override
	public void update(CartItem cartItem) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(cartItem);
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
