package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PaymentDaoimp;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentDaoimp paymentDao;
	
	
	public void getPaymentInfo() {
		
		 paymentDao.getPaymentInfo();
		
	}

}