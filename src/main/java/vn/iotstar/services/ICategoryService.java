package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryService {

	List<Category> findAll();
	
	Category findById(int id);
	
	void insert (Category cate);
	void delete (int category_id);
	void update (Category cate);
}
