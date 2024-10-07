package com.example.demo.model;


import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="creditcard_payment")
//@DiscriminatorValue("CC")
public class CreditCardPayment extends Payment{
	 private String cardNumber;
	 private String cardType;
	 
	 public CreditCardPayment(long id, Double amount, String currency, String cardNumber, String cardType) {
	        super(id, amount, currency);
	        this.cardNumber = cardNumber;
	        this.cardType = cardType;
	    }

}
