package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Order;

public interface IOrderService {
	
	Order insert(Order order);

	List<Order> findOrdersByUserId(int userId); // Lấy danh sách đơn hàng của người dùng
    Order findOrderById(int orderId); // Lấy chi tiết đơn hàng theo ID
    boolean cancelOrder(int orderId); // Hủy đơn hàng nếu trạng thái là "confirmed" hoặc "pending"
    boolean requestReturnOrder(int orderId);

}
