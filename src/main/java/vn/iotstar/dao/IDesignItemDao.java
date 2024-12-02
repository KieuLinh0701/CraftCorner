package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.DesignItem;
import vn.iotstar.entity.Designs;

public interface IDesignItemDao {
	
	void insert(DesignItem designItem);
	
	void delete(int id);
	
	List<DesignItem> findByIdDesign(int id);
}
