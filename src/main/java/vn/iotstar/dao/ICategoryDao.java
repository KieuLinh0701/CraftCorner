package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryDao {
	Category findByName(String name);
	void save(Category category);
	List<Category> findAll();
}
