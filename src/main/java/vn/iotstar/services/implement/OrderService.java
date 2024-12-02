package vn.iotstar.services.implement;

import java.util.List;

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
	
	@Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }
	
	@Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }
	
	@Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
