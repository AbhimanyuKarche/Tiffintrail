package com.cdac.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cdac.Repositories.TiffinRepository;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.Tiffin;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TiffinServiceImpl implements TiffinService {
	 private final TiffinRepository tiffinRepo;
	@Override
	public Tiffin createTiffin(SellerProfile seller, Tiffin tiffin) {
		tiffin.setSeller(seller);
        return tiffinRepo.save(tiffin);
	}

	@Override
	public List<Tiffin> getAllTiffins() {
		 return tiffinRepo.findAll();
    
	}

	@Override
	public List<Tiffin> getTiffinsBySeller(SellerProfile seller) {
		 return tiffinRepo.findBySeller(seller);
	}

	@Override
	public Optional<Tiffin> getTiffinById(Long id) {
		return tiffinRepo.findById(id);
	}

	@Override
	public void deleteTiffin(Long id) {
		 tiffinRepo.deleteById(id);

	}

}
