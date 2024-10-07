package com.example.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;
import com.example.demo.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDaoimp implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Create a new product
    @Override
    public boolean createProduct(Product product2) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class, product2.getUserid());
            if (user != null) {
                Product product = Product.builder()
                        .name(product2.getName())
                        .description(product2.getDescription())
                        .price(product2.getPrice())
                        .userid(user)
                        .build();
                session.save(product);
                tx.commit();
                return true;
            } else {
                throw new Exception("User doesn't exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    // Get a product by ID
    @Override
    public ProductResponse getProductById(long productId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Product product = session.get(Product.class, productId);
            ProductResponse productResponse = ProductResponse.builder()
                    .id(product.getId()) 
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .userId(product.getUserid().getId())
                    .build();
            tx.commit();
            return productResponse;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    // Search for products by name or description
    @Override
    public List<ProductResponse> searchProducts(String query) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Product> products = session.createQuery("FROM Product WHERE name LIKE :query OR description LIKE :query", Product.class)
                    .setParameter("query", "%" + query + "%")
                    .list();
            List<ProductResponse> productResponses = products.stream()
                    .map(product -> ProductResponse.builder()
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .userId(product.getUserid().getId())
                            .build())
                    .collect(Collectors.toList());
            tx.commit();
            return productResponses;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    // Get all products from the database
    @Override
    public List<ProductResponse> getAllProducts() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            List<ProductResponse> productResponses = products.stream()
                    .map(product -> ProductResponse.builder()
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .userId(product.getUserid().getId())
                            .build())
                    .collect(Collectors.toList());
            tx.commit();
            return productResponses;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

	@Override
	public boolean createProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		return false;
	}
}
