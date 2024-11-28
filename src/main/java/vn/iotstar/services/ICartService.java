package vn.iotstar.services;

import vn.iotstar.entity.Cart;

public interface ICartService {

	Cart findByUser(int user_id);
	
	void delete(int id) throws Exception;
}
