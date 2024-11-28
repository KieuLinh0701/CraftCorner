package vn.iotstar.dao.implement;

import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.ICartItemDao;
import vn.iotstar.entity.CartItem;

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

}
