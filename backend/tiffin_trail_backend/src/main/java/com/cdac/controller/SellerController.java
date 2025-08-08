package com.cdac.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.UserRepository;
import com.cdac.dto.SellerRequestDto;
import com.cdac.dto.SellerResponseDto;
import com.cdac.entity.User;
import com.cdac.services.SellerProfileServiceImpl;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/seller")
public class SellerController {
	
	//dependency
	 private final SellerProfileServiceImpl sellerProfileService;
	 private final UserRepository userRepository;

	   
	    @PostMapping("/profile")
	    public ResponseEntity<SellerResponseDto> createProfile(@RequestBody SellerRequestDto sellerRequestDto ) {
	        
	        SellerResponseDto savedProfile = sellerProfileService.createProfile( sellerRequestDto);
	        return ResponseEntity.ok(savedProfile);
	    }

	    @GetMapping("/profile/{userId}")
	    public ResponseEntity<SellerResponseDto> getProfile(@PathVariable Long userId) {
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        SellerResponseDto profile = sellerProfileService.getByUser(user)
	               .orElseThrow(() -> new RuntimeException("Seller profile not found"));

	        return ResponseEntity.ok(profile);
	    }
}
