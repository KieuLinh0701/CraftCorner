package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IOrderDao;
import vn.iotstar.dao.implement.OrderDao;
import vn.iotstar.entity.Order;
import vn.iotstar.services.IOrderService;

public class OrderService implements IOrderService {

	IOrderDao orderDao = new OrderDao();

	@Override
	public Order insert(Order order) {
		return orderDao.insert(order);
	}

	@Override
	public List<Order> findOrdersByUserId(int userId) {
		return orderDao.findOrdersByUserId(userId);
	}

	@Override
	public Order findOrderById(int orderId) {
		return orderDao.findOrderById(orderId);
	}

	@Override
	public boolean cancelOrder(int orderId) {
		Order order = orderDao.findOrderById(orderId);
		if (order != null) {
			String status = order.getStatus();
			if ("confirmed".equalsIgnoreCase(status) || "pending".equalsIgnoreCase(status)) {
				order.setStatus("cancelled");
				orderDao.updateOrder(order);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean requestReturnOrder(int orderId) {
		Order order = orderDao.findOrderById(orderId);

		if (order != null && "Completed".equals(order.getStatus())) {
			order.setStatus("Request for Return and Refund");
			orderDao.updateOrder(order);
			return true;
		}
		return false;
	}
}
