package vn.iotstar.dao;

import java.util.List;


import vn.iotstar.entity.Product;

public interface IProductDao {
	List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> searchProductsByName(String name);
    List<Product> searchProductsByPriceRange(double minPrice, double maxPrice);
    List<Product> searchProductsByQuantityRange(int minQuantity, int maxQuantity);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> findProductsByCategory(String category);
}
