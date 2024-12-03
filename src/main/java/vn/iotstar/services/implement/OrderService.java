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

	public Long getTotalOrders(String periodType, String periodValue) {
        return orderDao.getTotalOrders(periodType, periodValue);
    }

    public Double getTotalRevenue(String periodType, String periodValue) {
        return orderDao.getTotalRevenue(periodType, periodValue);
    }

    public String getHighestRevenueProduct(String periodType, String periodValue) {
        return orderDao.getHighestRevenueProduct(periodType, periodValue);
    }

    public String getLowestRevenueProduct(String periodType, String periodValue) {
        return orderDao.getLowestRevenueProduct(periodType, periodValue);
    }

    public String getMostPurchasedProduct(String periodType, String periodValue) {
        return orderDao.getMostPurchasedProduct(periodType, periodValue);
    }

    public String getLeastPurchasedProduct(String periodType, String periodValue) {
        return orderDao.getLeastPurchasedProduct(periodType, periodValue);
    }
    @Override
    public Object[] getOrderDetailsByPeriod(String periodType, String periodValue) {
        try {
            // Gọi phương thức của DAO để lấy số lượng đơn hàng và tổng doanh thu
            return orderDao.getOrderDetailsByPeriod(periodType, periodValue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving order details by period", e);
        }
    }
}
