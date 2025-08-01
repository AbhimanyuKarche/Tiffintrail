package com.cdac.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponseDto {

	
		private Long orderId;
		
		private LocalDateTime dateTime;
		
		private String feedback;
		
		private String mobile;
		
		private String status;
		
		private int quantity;
		private double amount;
		private long userId;
		
		

	
}
