package com.cdac.controller;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.SellerRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;
import com.cdac.enums.RequestStatus;
import com.cdac.enums.Role;
import com.cdac.services.SellerProfileServiceImpl;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/seller")
public class SellerController {

    private final SellerProfileServiceImpl sellerProfileService;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

   

    //  POST /api/seller/profile - Create seller profile using JWT
    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(
            @RequestBody SellerProfile sellerProfile,
            Principal principal) {

        //  Extract email from JWT
        String email = principal.getName();

        //  Lookup User by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Ensure user has SELLER role
        if (!user.getRole().equals(Role.SELLER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only sellers can create a profile");
        }

        //  Check if seller profile already exists
        if (sellerRepository.findByUser(user).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seller profile already exists");
        }

        //  Set user and approval status
        sellerProfile.setUser(user);
        sellerProfile.setApprovalStatus(RequestStatus.PENDING); // This is the missing piece

        //  Save and return
        SellerProfile savedProfile = sellerProfileService.createProfile(user, sellerProfile);
        return ResponseEntity.ok(savedProfile);
    }

    //  GET /api/seller/profile - Get seller profile of logged-in user (no path variable)
    @GetMapping("/profile")
    public ResponseEntity<SellerProfile> getProfile(Principal principal) {
        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SellerProfile profile = sellerProfileService.getByUser(user)
                .orElseThrow(() -> new RuntimeException("Seller profile not found"));

        return ResponseEntity.ok(profile);
    }
    @GetMapping("/approved")
    public ResponseEntity<List<SellerProfile>> getApprovedSellers() {
        List<SellerProfile> approvedSellers = sellerRepository.findByApprovalStatus(RequestStatus.APPROVED);
        return ResponseEntity.ok(approvedSellers);
    }
}

