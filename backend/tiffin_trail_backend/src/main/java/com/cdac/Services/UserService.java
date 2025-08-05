package com.cdac.Services;

import java.util.Optional;

import com.cdac.dto.UserRequestDto;
import com.cdac.dto.UserResponseDto;
import com.cdac.entity.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> findByEmail(String email);
	
}
