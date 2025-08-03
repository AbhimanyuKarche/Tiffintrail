package com.cdac.Services;

import java.util.List;
import java.util.Optional;

import com.cdac.dto.SellerRequestDto;
import com.cdac.dto.TiffinRequestDto;
import com.cdac.dto.TiffinResponseDto;
import com.cdac.entity.SellerProfile;

public interface TiffinService {
	TiffinResponseDto  createTiffin(SellerProfile seller, TiffinRequestDto  tiffin);
	    List<TiffinResponseDto> getAllTiffins();
	    List<TiffinResponseDto> getTiffinsBySeller(SellerProfile seller);
	    Optional<TiffinResponseDto> getTiffinById(Long id);
	    void deleteTiffin(Long id);
}
