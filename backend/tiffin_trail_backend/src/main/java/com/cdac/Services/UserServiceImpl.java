package com.cdac.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Repositories.UserRepository;
import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserResponseDto;
import com.cdac.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public UserResponseDto saveUser(UserRequestDto userRequestDto) {
		 // 1. Convert DTO to Entity
        User user = new User();
        user.setFullName(userRequestDto.getFullName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        // set other fields...

        // 2. Save entity
        User savedUser = userRepository.save(user);

        // 3. Convert Entity to Response DTO
        UserResponseDto response = new UserResponseDto();
        response.setId(savedUser.getId());
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        // set other fields...

        return response;
	}
	

	

}
