package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;

@Repository
public class OrderDaoimpl implements OrderDao{

	@Override
	public boolean createOrder(OrderRequest orderRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderResponse getOrderById(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}