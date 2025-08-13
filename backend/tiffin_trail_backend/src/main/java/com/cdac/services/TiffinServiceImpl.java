package com.cdac.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.TiffinRepository;
import com.cdac.dto.TiffinResponseDTO;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.Tiffin;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TiffinServiceImpl implements TiffinService  {
    private final TiffinRepository tiffinRepo;

    @Override
    public Tiffin createTiffin(SellerProfile seller, Tiffin tiffin) {
        tiffin.setSeller(seller);
        return tiffinRepo.save(tiffin);
    }

    public List<TiffinResponseDTO> getAllTiffins() {
        List<Tiffin> tiffins = tiffinRepo.findAll();

        return tiffins.stream().map(tiffin -> {
            SellerProfile seller = tiffin.getSeller();
            User user = seller.getUser(); // assuming this exists

            return new TiffinResponseDTO(
                    tiffin.getId(),
                    tiffin.getName(),
                    tiffin.getDescription(),
                    tiffin.getPrice(),
                    user.getFullName(),
                    user.getEmail(),
                    tiffin.getImageUrl()
            );
        }).collect(Collectors.toList());
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
