package com.cdac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.SellerRepository;
import com.cdac.entity.SellerProfile;
import com.cdac.enums.RequestStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SellerRepository sellerProfileRepository;

    // 1. Get all pending seller requests
    @GetMapping("/pending-sellers")
    public ResponseEntity<List<SellerProfile>> getPendingSellers() {
        List<SellerProfile> pendingSellers = sellerProfileRepository.findByApprovalStatus(RequestStatus.PENDING);
        return ResponseEntity.ok(pendingSellers);
    }

    // 2. Approve or Reject a seller
    @PostMapping("/seller/{id}/status")
    public ResponseEntity<String> updateSellerStatus(
            @PathVariable Long id,
            @RequestParam("status") RequestStatus status) {

        SellerProfile profile = sellerProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        profile.setApprovalStatus(status);
        sellerProfileRepository.save(profile);

        return ResponseEntity.ok("Seller status updated to: " + status);
    }
}

