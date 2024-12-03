package vn.iotstar.controllers;

import java.io.IOException;
<<<<<<< HEAD
import java.util.Set;

=======
import java.util.List;
import vn.iotstar.services.IProductService;
import vn.iotstar.services.implement.ProductService;
>>>>>>> bb464d9d454c6b4c10a0818a852cbe48049708fa
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.User;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.implement.CartService;
import vn.iotstar.utils.Constant;
import vn.iotstar.entity.Product;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

<<<<<<< HEAD
	private static final long serialVersionUID = 1L;
	public ICartService cartService = new CartService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cartItemCount = 0;
		if (session != null && session.getAttribute("account") != null) 
		{
			User u = (User) session.getAttribute("account"); 
			Cart cart = cartService.findByUser(u.getId());
			if (cart == null) 
			{
				cartItemCount=0;
			} 
			else 
			{
				Set<CartItem> listCartItem = cart.getCartItems();
				cartItemCount = listCartItem.size();
			}
		}
		req.getSession().setAttribute("cartItemCount", cartItemCount);
		req.getRequestDispatcher(Constant.HOME).forward(req, resp);
	}
=======
    private static final long serialVersionUID = 1L;
>>>>>>> bb464d9d454c6b4c10a0818a852cbe48049708fa

    // ProductService to fetch products from database
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch the product list from the database
        List<Product> products = productService.getAllProducts();

        // Set the products as a request attribute so it can be accessed in JSP
        req.setAttribute("products", products);

        // Forward the request to the homepage JSP
        req.getRequestDispatcher(Constant.HOME).forward(req, resp);
    }
}
