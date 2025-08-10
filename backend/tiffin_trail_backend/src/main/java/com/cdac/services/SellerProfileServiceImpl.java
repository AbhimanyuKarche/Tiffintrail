package com.cdac.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.SellerRepository;
import com.cdac.Repositories.UserRepository;
import com.cdac.dto.SellerRequestDto;
import com.cdac.dto.SellerResponseDto;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class SellerProfileServiceImpl implements SellerProfileService {
	private final SellerRepository sellerRepo;
	private final UserRepository userRepository;
	 private final ModelMapper modelMapper;
	
	@Override
	public SellerResponseDto createProfile(User user,SellerRequestDto dto) {
		
        //mapping dto to entity using model mapper
        SellerProfile profile = modelMapper.map(dto, SellerProfile.class);
        profile.setUser(user);// User is not in DTO, must set manually

        
      // 2. Save entity
        SellerProfile savedProfile = sellerRepo.save(profile);
        
        
     //3. Convert Entity to Response DTO using model mapper
        SellerResponseDto response = modelMapper.map(savedProfile, SellerResponseDto.class);
        response.setUserId(profile.getUser().getId());
       response.setUserEmail(profile.getUser().getEmail());
       
        
        return response;
	}

	@Override
	public Optional<SellerResponseDto> getByUser(User user) {
		SellerProfile profile = sellerRepo.findByUser(user)
        		.orElseThrow(()-> new RuntimeException("Seller profile not found"));
        
      // Convert Entity to Response DTO using model mapper
        SellerResponseDto response = modelMapper.map(profile, SellerResponseDto.class);
       response.setUserId(profile.getUser().getId());
       response.setUserEmail(profile.getUser().getEmail());
       
        // set other fields...
        
        return Optional.of(response);
	}

}
