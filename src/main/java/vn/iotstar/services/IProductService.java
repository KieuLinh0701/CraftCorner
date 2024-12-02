package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.Product;

public interface IProductService {
	List<Product> getAllProducts();

    Product getProductById(int productId); 
}
