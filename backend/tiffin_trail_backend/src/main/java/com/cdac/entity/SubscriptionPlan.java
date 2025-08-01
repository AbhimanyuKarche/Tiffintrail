package com.cdac.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cdac.enums.PlanType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@Entity
@Table(name = "subscription_plan")

public class SubscriptionPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;
	@NotNull
	private Long custId;
	@Enumerated(EnumType.STRING)
	@NotNull
	private PlanType planType;
	@PastOrPresent
	@NotNull
	private LocalDate startDate;
	@Future
	@NotNull
	private LocalDate endDate;
	@NotBlank
	private String status;
	@NotBlank
	private String description;
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	
	

	
	

	
	
}
