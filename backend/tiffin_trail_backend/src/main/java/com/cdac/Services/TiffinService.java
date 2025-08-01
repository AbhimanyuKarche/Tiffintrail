package com.cdac.Services;

import java.util.List;
import java.util.Optional;

import com.cdac.entity.SellerProfile;
import com.cdac.entity.Tiffin;

public interface TiffinService {
	 Tiffin createTiffin(SellerProfile seller, Tiffin tiffin);
	    List<Tiffin> getAllTiffins();
	    List<Tiffin> getTiffinsBySeller(SellerProfile seller);
	    Optional<Tiffin> getTiffinById(Long id);
	    void deleteTiffin(Long id);
}
