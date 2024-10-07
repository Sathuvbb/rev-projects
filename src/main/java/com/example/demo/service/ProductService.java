package com.example.demo.service;




import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDaoimp;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDaoimp productDAO;
	
	
	public boolean createProduct(ProductRequest productRequest) {
		
		return productDAO.createProduct(productRequest);
		
	}
	
	public ProductResponse getProductById(long id) {
		
		return productDAO.getProductById(id);
		
	}
	public List<ProductResponse> searchProducts(String query) {
        return productDAO.searchProducts(query);
    }
	 // Get all products
    public List<ProductResponse> getAllProducts() {
        // Fetching all products from the ProductDaoImp
        return productDAO.getAllProducts();
    }
    private final ProductDaoimp productDao;

    @Autowired
    public ProductService(ProductDaoimp productDao) {
        this.productDao = productDao;
    }

    public boolean saveProduct(Product product) {
        return productDao.createProduct(product);

	}
}


