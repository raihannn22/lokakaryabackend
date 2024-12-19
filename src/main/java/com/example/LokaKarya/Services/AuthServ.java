package com.example.lokakarya.Services;

import java.util.Optional;

import com.example.lokakarya.Dto.ChangePassDto;
import com.example.lokakarya.Dto.LoginDto;
import com.example.lokakarya.Dto.LoginResponseDto;
import com.example.lokakarya.Dto.User.UserDto;
import com.example.lokakarya.Entity.User;

public interface AuthServ{
    LoginResponseDto login(LoginDto data);
    UserDto changePassword(ChangePassDto data);
}
