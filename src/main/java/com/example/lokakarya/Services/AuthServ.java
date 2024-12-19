package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.Auth.ChangePassDto;
import com.example.lokakarya.Dto.Auth.LoginDto;
import com.example.lokakarya.Dto.Auth.LoginResponseDto;
import com.example.lokakarya.Dto.User.UserDto;

public interface AuthServ{
    LoginResponseDto login(LoginDto data);
    UserDto changePassword(ChangePassDto data);
}
