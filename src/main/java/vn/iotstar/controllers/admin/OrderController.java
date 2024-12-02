package vn.iotstar.controllers.admin;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Order;
import vn.iotstar.services.IOrderService;
import vn.iotstar.services.implement.OrderService;

@WebServlet(urlPatterns = {
    "/admin/orders", 
    "/admin/order/add",
    "/admin/order/insert",
    "/admin/order/edit",
    "/admin/order/update",
    "/admin/order/delete"
})
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private IOrderService orderService = new OrderService();
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/admin/orders")) {
            List<Order> orders = orderService.findAll();
            req.setAttribute("listOrders", orders);
            req.getRequestDispatcher("/views/admin/OrderManagement/orderlist.jsp").forward(req, resp);
        } else if (url.contains("/admin/order/add")) {
            req.getRequestDispatcher("/views/orderadd.jsp").forward(req, resp);
        } else if (url.contains("/admin/order/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Order order = orderService.findById(id);
            req.setAttribute("order", order);
            req.getRequestDispatcher("/views/orderedit.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("/admin/order/insert")) {
            Order order = new Order();
            order.setOrder_date(new Date());
            order.setTotal_price(Double.parseDouble(req.getParameter("total_price")));
            order.setNote(req.getParameter("note"));
            order.setStatus(req.getParameter("status"));

            // Thêm user, payment và promote vào nếu cần
            // Ví dụ:
            // order.setUser(userService.findById(Integer.parseInt(req.getParameter("user_id"))));
            // order.setPayment(paymentService.findById(Integer.parseInt(req.getParameter("payment_id"))));

            orderService.insert(order);
            resp.sendRedirect(req.getContextPath() + "/admin/orders");
        } else if (url.contains("/admin/order/update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Order order = orderService.findById(id);
            order.setTotal_price(Double.parseDouble(req.getParameter("total_price")));
            order.setNote(req.getParameter("note"));
            order.setStatus(req.getParameter("status"));

            orderService.update(order);
            resp.sendRedirect(req.getContextPath() + "/admin/orders");
        }
        
    }
}
