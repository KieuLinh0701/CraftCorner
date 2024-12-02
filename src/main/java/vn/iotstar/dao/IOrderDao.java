package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Order;

public interface IOrderDao {

	Order insert(Order order);

	List<Order> findOrdersByUserId(int userId); // Tìm đơn hàng của người dùng theo userId
    Order findOrderById(int orderId); // Tìm đơn hàng theo ID
    void updateOrder(Order order); // Cập nhật đơn hàng


}
