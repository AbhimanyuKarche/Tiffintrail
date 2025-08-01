package com.cdac.Services;

import com.cdac.dto.SellerRequestDto;
import com.cdac.dto.SellerResponseDto;
import com.cdac.entity.User;

public interface SellerProfileService {
	SellerResponseDto createProfile(SellerRequestDto dto);
	SellerResponseDto getByUser(User user);
}
