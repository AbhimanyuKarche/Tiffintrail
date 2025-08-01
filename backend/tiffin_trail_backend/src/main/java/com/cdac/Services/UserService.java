package com.cdac.Services;

import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserResponseDto;

public interface UserService {
	UserResponseDto saveUser(UserRequestDto user);
}
