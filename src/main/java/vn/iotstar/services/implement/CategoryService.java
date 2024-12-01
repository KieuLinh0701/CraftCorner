package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.implement.CategoryDao;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;

public class CategoryService implements ICategoryService{

	private ICategoryDao cateDao = new CategoryDao();
	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}
	
}
