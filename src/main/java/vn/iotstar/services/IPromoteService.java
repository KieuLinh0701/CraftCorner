package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Promote;

public interface IPromoteService {
	
	List<Promote> findAll();
	
	List<Promote> findPromoteForOrder(int totalPrice);
	
	Promote findById(int id);
	
	void update(Promote promote);
}
