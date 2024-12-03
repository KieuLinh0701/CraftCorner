package vn.iotstar.dao;

import java.util.List;
<<<<<<< HEAD

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

=======
import vn.iotstar.entity.Product;

public interface IProductDao {
	List<Product> getAllProducts(); 
    List<Product> getNewArrivals();
    Product getProductById(int productId);
    List<Product> getRelatedProducts(int productId);
>>>>>>> bb464d9d454c6b4c10a0818a852cbe48049708fa
}
