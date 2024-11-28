package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.PaymentMethod;

public interface IPaymentDao {

	List<PaymentMethod> findAll();
	
	List<PaymentMethod> findPaymentActive();
	
	PaymentMethod findById(int payment_id);
}
