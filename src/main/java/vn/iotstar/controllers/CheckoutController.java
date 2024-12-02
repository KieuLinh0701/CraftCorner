package vn.iotstar.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.Address;
import vn.iotstar.entity.Cart;
import vn.iotstar.entity.CartItem;
import vn.iotstar.entity.Order;
import vn.iotstar.entity.OrderDetail;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.entity.Product;
import vn.iotstar.entity.Promote;
import vn.iotstar.entity.User;
import vn.iotstar.services.IAddressService;
import vn.iotstar.services.ICartItemService;
import vn.iotstar.services.ICartService;
import vn.iotstar.services.IOrderDetailService;
import vn.iotstar.services.IOrderService;
import vn.iotstar.services.IPaymentService;
import vn.iotstar.services.IProductService;
import vn.iotstar.services.IPromoteService;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.implement.AddressService;
import vn.iotstar.services.implement.CartItemService;
import vn.iotstar.services.implement.CartService;
import vn.iotstar.services.implement.OrderDetailService;
import vn.iotstar.services.implement.OrderService;
import vn.iotstar.services.implement.PaymentService;
import vn.iotstar.services.implement.ProductService;
import vn.iotstar.services.implement.PromoteService;
import vn.iotstar.services.implement.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/checkout", "/checkout/updateaddress", "/order" })
public class CheckoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ICartService cartService = new CartService();
	public ICartItemService cartItemService = new CartItemService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("checkout")) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			req.setAttribute("user", user);

			IPaymentService paymentService = new PaymentService();
			List<PaymentMethod> listPayment = paymentService.findPaymentActive();
			req.setAttribute("listPayment", listPayment);

			Cart cart = cartService.findByUser(user.getId());
			Set<CartItem> listCartItem = cart.getCartItems();
			req.setAttribute("listCartItem", listCartItem);
			
			int total = cartItemService.totalPrice(listCartItem);
			req.setAttribute("total", total);
			
			IPromoteService promoteService = new PromoteService();
			List<Promote> listPromote = promoteService.findPromoteForOrder(total);
			req.setAttribute("listPromote", listPromote);
			
			req.getRequestDispatcher(Constant.CHECKOUT).forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("checkout/updateaddress")) {
			String city = req.getParameter("city");
			String district = req.getParameter("district");
			String ward = req.getParameter("ward");
			String detail = req.getParameter("detail");
			int address_id = Integer.parseInt(req.getParameter("address_id"));
			
			IAddressService addressService = new AddressService();
			Address address = new Address(address_id, city, district, ward, detail);
			addressService.update(address);
			
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			user.setAddress(address);
			session.setAttribute("account", user);
			
			resp.sendRedirect(req.getContextPath() + "/checkout");
		} else if (url.contains("order")) {
			Order order = new Order();
			
			int user_id = Integer.parseInt(req.getParameter("user_id"));
			IUserService userService = new UserService();
			User user = userService.findById(user_id);
			order.setUser(user);
			
			int total_price = Integer.parseInt(req.getParameter("total_price"));
			order.setTotal_price(total_price);
			
			order.setOrder_date(new Date());
			
			order.setStatus("Pending");
			
			String note = req.getParameter("noteOrder");
			order.setNote(note);
			
			int payment_id = Integer.parseInt(req.getParameter("payment"));
			IPaymentService paymentService = new PaymentService();
			PaymentMethod payment = paymentService.findById(payment_id);
			order.setPayment(payment);
			
			String promoteIdString = req.getParameter("promote");
			if (promoteIdString != null) {
				int id = Integer.parseInt(promoteIdString);
				IPromoteService promoteService = new PromoteService();
				Promote promote = promoteService.findById(id);
				order.setPromote(promote);
				
				promote.setQuantityUsed(promote.getQuantityUsed()+1);
				promoteService.update(promote);
			}
			
			IOrderService orderService = new OrderService();
			Order newOrder = orderService.insert(order);
			
			Cart cart = cartService.findByUser(user_id);
			Set<CartItem> setCartItem = cart.getCartItems();
			
			IOrderDetailService detailService = new OrderDetailService();
			for (CartItem x:setCartItem) {
				OrderDetail orderDetail = new OrderDetail();
				
				IProductService productService = new ProductService();
				Product product = x.getProduct();
				product.setQuantity(product.getQuantity()-1);
				productService.update(product);
				
				orderDetail.setProduct(x.getProduct());
				orderDetail.setQuantity(x.getQuantity());
				orderDetail.setOrder(newOrder);
				orderDetail.setPrice(x.getProduct().getPrice());
				detailService.insert(orderDetail);
			}
			
			try {
				cartService.delete(cart.getCart_id());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (payment.getBankName().contains("COD")) {
				req.setAttribute("inform", "Thank you for your order. Your order will be shipped shortly");
				req.getRequestDispatcher(Constant.ORDER_SUCCESS).forward(req, resp);
			}
			else {
				req.setAttribute("inform", "Thanks for your order! Please make a bank transfer using the account details below");
				req.setAttribute("description",
								"Bank Name: " + payment.getBankName() + "\n" +
							    "Account Number: " + payment.getAccountNumber() + "\n" +
							    "Account Owner: " + payment.getAccountOwner());
				req.getRequestDispatcher(Constant.ORDER_SUCCESS).forward(req, resp);
			}
		}
	}

}
