package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Promote;

public interface IPromoteDao {
	
	List<Promote> findAll();
	
	List<Promote> findPromoteForOrder(int totalPrice);
	
	Promote findById(int id);
	
	void insert(Promote promote);

    void update(Promote promote);

    void delete(Promote promote);

}
