package com.cdac.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Repositories.SellerRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.Services.TiffinService;
import com.cdac.dto.SellerRequestDto;
import com.cdac.dto.TiffinRequestDto;
import com.cdac.dto.TiffinResponseDto;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tiffins")
public class TiffinController {
    private final TiffinService tiffinService;
    private final UserRepository userRepo;
    private final SellerRepository sellerRepo;

   
    //Add a new tiffin
    @PostMapping("/add/{userId}")
    public ResponseEntity<TiffinResponseDto> createTiffin(@PathVariable Long userId, @RequestBody TiffinRequestDto tiffin) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        SellerProfile seller = sellerRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Seller profile not found"));

        TiffinResponseDto createdTiffin = tiffinService.createTiffin(seller, tiffin);
        return ResponseEntity.ok(createdTiffin);
    }

    //Get all tiffins by seller
    @GetMapping("/seller/{userId}")
    public ResponseEntity<List<TiffinResponseDto>> getTiffinsBySeller(@PathVariable Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        SellerProfile seller = sellerRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Seller profile not found"));

        List<TiffinResponseDto> tiffins = tiffinService.getTiffinsBySeller(seller);
        return ResponseEntity.ok(tiffins);
    }

    //Get all tiffins (public)
    @GetMapping("/all")
    public ResponseEntity<List<TiffinResponseDto>> getAllTiffins() {
        return ResponseEntity.ok(tiffinService.getAllTiffins());
    }

    //(Optional) Delete a tiffin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTiffin(@PathVariable Long id) {
        tiffinService.deleteTiffin(id);
        return ResponseEntity.noContent().build();
    }
}
