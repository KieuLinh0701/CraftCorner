package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IPaymentDao;
import vn.iotstar.dao.implement.CategoryDao;
import vn.iotstar.dao.implement.PaymentDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IPaymentService;

public class CategoryService implements ICategoryService{

	ICategoryDao categoryDao = new CategoryDao();

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findById(int id) {
		return categoryDao.findById(id);
	}
	
	@Override
	public void insert(Category cate) {
		categoryDao.insert(cate);
		
	}
	@Override
	public void delete(int category_id) {
		categoryDao.delete(category_id);
		
	}
	@Override
	public void update(Category cate) {
		categoryDao.update(cate);
		
	}
}
