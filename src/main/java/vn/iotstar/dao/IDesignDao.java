package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Designs;

public interface IDesignDao {
	
	List<Designs> findAll();
	
	Designs findById(int id);
	
	void insert(Designs design);
	
	void update(Designs design);
	
	void delete(int id);
}
