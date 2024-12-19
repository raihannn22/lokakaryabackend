package com.example.lokakarya.Dto.Auth;

import com.example.lokakarya.Dto.User.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class LoginResponseDto {
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("token")
    private String token;
}
