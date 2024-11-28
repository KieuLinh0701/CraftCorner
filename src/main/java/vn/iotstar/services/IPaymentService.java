package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.PaymentMethod;

public interface IPaymentService {
	
	List<PaymentMethod> findAll();
	
	List<PaymentMethod> findPaymentActive();
	
	PaymentMethod findById(int payment_id);
}
