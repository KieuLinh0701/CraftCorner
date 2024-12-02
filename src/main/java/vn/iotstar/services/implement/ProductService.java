package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IDesignDao;
import vn.iotstar.dao.IProductDao;
import vn.iotstar.dao.implement.DesignDao;
import vn.iotstar.dao.implement.ProductDao;
import vn.iotstar.entity.Designs;
import vn.iotstar.entity.Product;
import vn.iotstar.services.IDesignService;
import vn.iotstar.services.IProductService;

public class ProductService implements IProductService {
	
	IProductDao productDao = new ProductDao();

	@Override
	public void insert(Product product) {
		productDao.insert(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

	@Override
	public List<Product> findByName(String keyword) {
		return productDao.findByName(keyword);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Product findById(int id) {
		return productDao.findById(id);
	}

	@Override
	public void delete(int id) {
		productDao.delete(id);
	}

	@Override
	public List<Product> findProductActive() {
		return productDao.findProductActive();
	}

	@Override
	public List<Product> findAll(int page, int pagesize) {
		return productDao.findAll(page, pagesize);
	}

	@Override
	public int countProduct(int pageSize) {
		return productDao.countProduct(pageSize);
	}

	@Override
	public int countProduct(int pageSize, String keyword) {
		return productDao.countProduct(pageSize, keyword);
	}

	@Override
	public List<Product> findByName(int page, int pagesize, String keyword) {
		return productDao.findByName(page, pagesize, keyword);
	}

	@Override
	public int productCount(int category_id) {
		return productDao.productCount(category_id);
	}

}
