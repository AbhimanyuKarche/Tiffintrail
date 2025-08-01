package com.cdac.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdac.enums.PaymentStatus;
import com.cdac.enums.PaymentType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	@NotNull
	private double payment;
	
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private PaymentType paymentType;
	
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private PaymentStatus status;
	
	
	@NotNull
	private LocalDateTime paymentTime;
	public Payment(Long paymentId, double payment,  PaymentType paymentType,
			 PaymentStatus status, LocalDateTime paymentTime) {
		super();
		this.paymentId = paymentId;
		this.payment = payment;
		this.paymentType = paymentType;
		this.status = status;
		this.paymentTime = paymentTime;
	}
	
	
	
	
}
