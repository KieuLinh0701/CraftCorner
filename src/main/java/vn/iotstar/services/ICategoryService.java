package vn.iotstar.services;

import vn.iotstar.entity.Category;

public interface ICategoryService {

	Category getCategoryByName(String name);

	void addCategory(vn.iotstar.entity.Category category);

}
