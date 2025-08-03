package com.cdac.Services;

import java.util.Optional;

import com.cdac.dto.SellerRequestDto;
import com.cdac.dto.SellerResponseDto;
import com.cdac.entity.User;

public interface SellerProfileService {
	SellerResponseDto createProfile(SellerRequestDto dto);
	Optional<SellerResponseDto >getByUser(User user);
}
