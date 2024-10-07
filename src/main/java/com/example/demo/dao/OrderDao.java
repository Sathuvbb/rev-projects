package com.example.demo.dao;

import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.OrderResponse;

import net.sf.ehcache.hibernate.EhCacheRegionFactory;

public interface OrderDao {
	
	boolean createOrder(OrderRequest orderRequest);
	OrderResponse getOrderById(Long orderId);
	

}