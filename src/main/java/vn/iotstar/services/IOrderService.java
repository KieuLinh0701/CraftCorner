package vn.iotstar.services;

import vn.iotstar.entity.Order;

public interface IOrderService {
	
	Order insert(Order order);
	Long getTotalOrders(String periodType, String periodValue);
	Double getTotalRevenue(String periodType, String periodValue);
	String getHighestRevenueProduct(String periodType, String periodValue);
	String getLowestRevenueProduct(String periodType, String periodValue);
	String getMostPurchasedProduct(String periodType, String periodValue);
	String getLeastPurchasedProduct(String periodType, String periodValue);
}
