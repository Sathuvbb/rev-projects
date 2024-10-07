package com.example.demo.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//for Single table
//@DiscriminatorColumn(name="payment_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// for table per class
//    @GeneratedValue(strategy = GenerationType.TABLE)

    private Long id;
	@Min(0)
    private double amount;
    private String currency;
    
}
