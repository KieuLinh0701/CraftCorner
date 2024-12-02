package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Designs;
import vn.iotstar.entity.Product;

public interface IProductDao {
	
	List<Product> findAll();
	
	Product findById(int id);
	
	void insert(Product product);
	
	void update(Product product);
	
	void delete(int id);
	
	List<Product> findByName(String keyword);
	
	List<Product> findProductActive();
	
	List<Product> findAll(int page, int pagesize);

	int countProduct(int pageSize);
	
	int countProduct(int pageSize, String keyword);
	
	List<Product> findByName(int page, int pagesize, String keyword);
	
	int productCount(int category_id);

}
