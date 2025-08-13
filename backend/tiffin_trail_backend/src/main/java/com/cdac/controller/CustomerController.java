package com.cdac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.UserRepository;
import com.cdac.entity.CustomerProfile;
import com.cdac.entity.User;
import com.cdac.services.CustomerProfileService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerProfileService customerProfileService;
    private final UserRepository userRepository;

    

    //  Create Customer Profile (JWT-Based)
    @PostMapping("/profile")
    public ResponseEntity<CustomerProfile> createProfile(@RequestBody CustomerProfile customerProfile,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CustomerProfile savedProfile = customerProfileService.createProfile(user, customerProfile);
        return ResponseEntity.ok(savedProfile);
    }

    //  Get Customer Profile
    @GetMapping("/profile")
    public ResponseEntity<CustomerProfile> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CustomerProfile profile = customerProfileService.getByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer profile not found"));

        return ResponseEntity.ok(profile);
    }
    @GetMapping("/profile/exists")
    public ResponseEntity<Boolean> profileExists(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean exists = customerProfileService.getByUser(user).isPresent();
        return ResponseEntity.ok(exists);
    }
}
