package com.cdac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class AuthResponse  {
    private String token;
    private String role;
    private Long userId;
}
