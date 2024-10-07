package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="googlepay_payment")
//@DiscriminatorValue("GP")
public class GooglePayPayment extends Payment{
	
	
	private String upiId;
	
	public GooglePayPayment(long id, Double amount, String currency, String upiId) {
        super(id, amount, currency);
        this.upiId = upiId;
    }

}