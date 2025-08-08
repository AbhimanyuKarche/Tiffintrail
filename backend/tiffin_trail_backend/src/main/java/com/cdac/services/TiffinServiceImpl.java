package com.cdac.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.SellerRepository;
import com.cdac.Repositories.TiffinRepository;
import com.cdac.dto.TiffinRequestDto;
import com.cdac.dto.TiffinResponseDto;
import com.cdac.entity.SellerProfile;
import com.cdac.entity.Tiffin;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TiffinServiceImpl implements TiffinService {

    private final TiffinRepository tiffinRepo;
    private final SellerRepository sellerRepo;
    private final ModelMapper modelMapper;

    @Override
    public TiffinResponseDto createTiffin(SellerProfile seller, TiffinRequestDto tiffinDto) {
        
        // Map TiffinReqDto to Tiffin entity
        Tiffin tiffin = modelMapper.map(tiffinDto, Tiffin.class);
        //set seller
        tiffin.setSeller(seller);

        // Save Tiffin
        Tiffin saved = tiffinRepo.save(tiffin);

        // Map back to response DTO
        return modelMapper.map(saved, TiffinResponseDto.class);
    }

    @Override
    public List<TiffinResponseDto> getAllTiffins() {
        return tiffinRepo.findAll()
                .stream()
                .map(tiffin -> modelMapper.map(tiffin, TiffinResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TiffinResponseDto> getTiffinsBySeller(SellerProfile seller) {
        
        return tiffinRepo.findBySeller(seller)
                .stream()
                .map(tiffin -> modelMapper.map(tiffin, TiffinResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TiffinResponseDto> getTiffinById(Long id) {
        return tiffinRepo.findById(id)
                .map(tiffin -> modelMapper.map(tiffin, TiffinResponseDto.class));
    }

    @Override
    public void deleteTiffin(Long id) {
        tiffinRepo.deleteById(id);
    }
}
