package vn.iotstar.services.implement;

import vn.iotstar.dao.IOrderDetailDao;
import vn.iotstar.dao.implement.OrderDetailDao;
import vn.iotstar.entity.OrderDetail;
import vn.iotstar.services.IOrderDetailService;
public class OrderDetailService implements IOrderDetailService{
	
	IOrderDetailDao orderDao = new OrderDetailDao();

	@Override
	public void insert(OrderDetail orderDetail) {
		orderDao.insert(orderDetail);
	}

}
