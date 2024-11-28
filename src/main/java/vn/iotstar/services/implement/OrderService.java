package vn.iotstar.services.implement;

import vn.iotstar.dao.IOrderDao;
import vn.iotstar.dao.implement.OrderDao;
import vn.iotstar.entity.Order;
import vn.iotstar.services.IOrderService;

public class OrderService implements IOrderService{
	
	IOrderDao orderDao = new OrderDao();

	@Override
	public Order insert(Order order) {
		return orderDao.insert(order);
	}

}
