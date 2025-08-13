package com.cdac.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cdac.Repositories.SellerRepository;
import com.cdac.Repositories.TiffinRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.Security.JwtTokenUtil;
import com.cdac.dto.TiffinResponseDTO;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.Tiffin;
import com.cdac.entity.User;
import com.cdac.enums.RequestStatus;
import com.cdac.services.TiffinService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tiffins")
public class TiffinController  {
    private final TiffinService tiffinService;
    private final UserRepository userRepo;
    private final SellerRepository sellerRepo;
    private final TiffinRepository tiffinRepository;

    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    //  Add a new tiffin
    @PreAuthorize("hasRole('SELLER')")
    @PostMapping("/add")
    public ResponseEntity<Tiffin> createTiffin(@RequestBody Tiffin tiffin, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // Remove "Bearer "
        String email = jwtTokenUtil.extractUsername(token);

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SellerProfile seller = sellerRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Seller profile not found"));
        
        if (seller.getApprovalStatus() != RequestStatus.APPROVED) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Seller not approved by admin");
        }

        Tiffin createdTiffin = tiffinService.createTiffin(seller, tiffin);
        return ResponseEntity.ok(createdTiffin);
    }


    //  Get all tiffins by seller
    @GetMapping("/seller/my-tiffins")
    public ResponseEntity<List<Tiffin>> getMyTiffins(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername(); // JWT gives you the email
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        SellerProfile seller = sellerRepo.findByUser(user).orElseThrow(() -> new RuntimeException("Seller profile not found"));

        List<Tiffin> tiffins = tiffinService.getTiffinsBySeller(seller);
        return ResponseEntity.ok(tiffins);
    }


    //  Get all tiffins (public)
    @GetMapping("/all")
    public ResponseEntity<List<TiffinResponseDTO>> getAllTiffins() {
        return ResponseEntity.ok(tiffinService.getAllTiffins());
    }

    @GetMapping("/seller/{sellerId}")
    public List<TiffinResponseDTO> getTiffinsBySeller(@PathVariable Long sellerId) {
        List<Tiffin> tiffins = tiffinRepository.findBySellerId(sellerId);

        return tiffins.stream()
                .map(tiffin -> new TiffinResponseDTO(
                        tiffin.getId(),
                        tiffin.getName(),
                        tiffin.getDescription(),
                        tiffin.getPrice(),
                        tiffin.getSeller().getUser().getFullName(),
                        tiffin.getSeller().getUser().getEmail(),
                        tiffin.getImageUrl()
                ))
                .collect(Collectors.toList());
    }





    //  (Optional) Delete a tiffin by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTiffin(@PathVariable Long id) {
        tiffinService.deleteTiffin(id);
        return ResponseEntity.noContent().build();
    }
}
