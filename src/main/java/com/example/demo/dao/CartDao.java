package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.CartRequest;
import com.example.demo.dto.CartResponse;

public interface CartDao {
	boolean addCart(CartRequest cartRequest) ;
    CartResponse getCartById(int id);
    boolean updateCart(CartRequest cartRequest); 
    boolean deleteCartById(int id) ;
    List<CartResponse> getAllCarts() ;
	void addToCart(Integer userId, int productId);
	void addToCart(int userId, int productId, int quantity);
}
