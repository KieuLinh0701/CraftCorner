package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Product;

public interface IProductService {
	List<Product> getAllProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    boolean isSameCategory(Product p1, Product p2);
    List<Product> findProductsByCategory(String category);
}
