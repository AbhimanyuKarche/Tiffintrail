package com.cdac.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.UserRepository;
import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserResponseDto;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        // Map request DTO to User entity
        User user = modelMapper.map(userRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setActive(true); // set default or from DTO

        // Save entity
        User savedUser = userRepository.save(user);

        // Map saved User to response DTO
        return modelMapper.map(savedUser, UserResponseDto.class);
    }
	
    @Override
    public Optional<UserResponseDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserResponseDto.class));
    }
	

}
