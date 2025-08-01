package com.cdac.Services;

import org.springframework.stereotype.Service;

import com.cdac.Repositories.SellerRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.dto.SellerRequestDto;
import com.cdac.dto.SellerResponseDto;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class SellerProfileServiceImpl implements SellerProfileService {
	private final SellerRepository sellerRepo;
	private final UserRepository userRepository;
	
	@Override
	public SellerResponseDto createProfile(SellerRequestDto dto) {
		// 1. Convert DTO to Entity
		User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SellerProfile profile = new SellerProfile();
        profile.setUser(user);
        profile.setBusinessName(dto.getBusinessName());
        profile.setDescription(dto.getDescription());
        profile.setAddress(dto.getAddress());
        profile.setCity(dto.getCity());
        profile.setPincode(dto.getPincode());
        profile.setPhone(dto.getPhone());
        
     // 2. Save entity
        SellerProfile savedProfile = sellerRepo.save(profile);
        
        
     //3. Convert Entity to Response DTO
        SellerResponseDto response = new SellerResponseDto();
       response.setId(savedProfile.getId());
       response.setBusinessName(savedProfile.getBusinessName());
       response.setDescription(savedProfile.getDescription());
       response.setAddress(savedProfile.getAddress());
       response.setCity(savedProfile.getCity());
       response.setPincode(savedProfile.getPincode());
       response.setPhone(savedProfile.getPhone());
       response.setUserId(profile.getUser().getId());
       response.setUserEmail(profile.getUser().getEmail());
        // set other fields...
        
        return response;
	}

	@Override
	public SellerResponseDto getByUser(User user) {
        SellerResponseDto profile= sellerRepo.findByUser(user);
        
      // Convert Entity to Response DTO
        SellerResponseDto response = new SellerResponseDto();
       response.setId(profile.getId());
       response.setBusinessName(profile.getBusinessName());
       response.setDescription(profile.getDescription());
       response.setAddress(profile.getAddress());
       response.setCity(profile.getCity());
       response.setPincode(profile.getPincode());
       response.setPhone(profile.getPhone());
       response.setUserId(profile.getUserId());
       response.setUserEmail(profile.getUserEmail());
       
        // set other fields...
        
        return response;
	}

}
