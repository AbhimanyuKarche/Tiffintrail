package com.cdac.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.cdac.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long orderId;
	@NotNull
	private LocalDate date;
	@NotBlank
	private String feedback;
	@NotBlank
	@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
	//@Length(min = 10, max = 10)
	private String mobile;
	@Enumerated(EnumType.STRING)
	@NotNull
	private OrderStatus status;
	@NotNull
	private int quantity;
	@ManyToOne
	@JoinColumn(name="payment_id",nullable=false)
	private Payment payment;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	public Order( Long orderId,  LocalDate date,  String feedback,
			 String mobile,  OrderStatus status,  int quantity,
			Payment payment, User user
	
			) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.feedback = feedback;
		this.mobile = mobile;
		this.status = status;
		this.quantity = quantity;
		this.payment = payment;
        this.user = user;
	}
	
	
}
