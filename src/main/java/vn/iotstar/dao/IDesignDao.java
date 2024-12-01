package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Designs;

public interface IDesignDao {
	
	List<Designs> findAll();
	
	Designs findById(int id);
	
	Designs insert(Designs design);
	
	void update(Designs design);
	
	void delete(int id);
	
	List<Designs> findByTitle(String keyword);
	
	List<Designs> findAll(int page, int pagesize);

	int countDesign(int pageSize);
	
	int countDesign(int pageSize, String keyword);
	
	List<Designs> findByTitle(int page, int pagesize, String keyword);
}
