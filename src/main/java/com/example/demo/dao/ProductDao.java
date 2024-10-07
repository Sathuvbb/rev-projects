package com.example.demo.dao;



import java.util.List;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;

public interface ProductDao {
	
	boolean createProduct(ProductRequest productRequest);
	ProductResponse getProductById(long productId);
	List<ProductResponse> searchProducts(String query);
	List<ProductResponse> getAllProducts();
	boolean createProduct(Product product2);

}
