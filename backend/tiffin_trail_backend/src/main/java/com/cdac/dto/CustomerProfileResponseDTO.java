package com.cdac.dto;

public class CustomerProfileResponseDTO {
    private Long id;
    private UserResponseDto user;
    private String address;
    private String city;
    private String pincode;
    private String phone;
    private CartResponseDto cart; // Minimal cart info (optional)
}