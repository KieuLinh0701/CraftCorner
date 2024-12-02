package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.entity.Product;

public interface IProductDao {
	List<Product> getAllProducts(); 
    List<Product> getNewArrivals();
    Product getProductById(int productId);  
}
