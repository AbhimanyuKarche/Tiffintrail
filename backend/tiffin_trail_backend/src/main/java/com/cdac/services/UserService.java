package com.cdac.services;

import java.util.Optional;

import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserResponseDto;
import com.cdac.entity.User;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto userRequestDto);
    Optional<UserResponseDto> findByEmail(String email);
	
}
