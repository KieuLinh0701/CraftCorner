package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Order;

public interface IOrderService {
	
	Order insert(Order order);

	List<Order> findAll();

	Order findById(int id);

	void update(Order order);
	
}
