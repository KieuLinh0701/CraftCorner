package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.User;
import vn.iotstar.services.ICartItemService;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.implement.CartItemService;
import vn.iotstar.services.implement.CartService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/cart"})
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ICartService cartService = new CartService();
	public ICartItemService cartItemService = new CartItemService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("cart")) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			
			if (user == null) {
				req.setAttribute("alert", "To access your shopping cart, please log in to your account!");
			} else {
				Cart cart = cartService.findByUser(user.getId());
				if (cart == null) {
					req.setAttribute("alert", "Your shopping cart is currently empty. Start shopping now!");
				} else {
					Set<CartItem> listCartItem = cart.getCartItems();
					req.setAttribute("listCartItem", listCartItem);
					
		            int total = cartItemService.totalPrice(listCartItem);
					req.setAttribute("total", total);
				}
			}
			req.getRequestDispatcher(Constant.CART).forward(req, resp);
		}
		
	}

}
