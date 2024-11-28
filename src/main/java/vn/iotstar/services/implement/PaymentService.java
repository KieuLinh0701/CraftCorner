package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IPaymentDao;
import vn.iotstar.dao.implement.PaymentDao;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.services.IPaymentService;

public class PaymentService implements IPaymentService{

	IPaymentDao paymentDao = new PaymentDao();

	@Override
	public List<PaymentMethod> findAll() {
		return paymentDao.findAll();
	}

	@Override
	public PaymentMethod findById(int payment_id) {
		return paymentDao.findById(payment_id);
	}

	@Override
	public List<PaymentMethod> findPaymentActive() {
		return paymentDao.findPaymentActive();
	}
}
