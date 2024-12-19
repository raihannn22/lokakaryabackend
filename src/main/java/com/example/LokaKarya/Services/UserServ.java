package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.lokakarya.Dto.User.UserDto;
import com.example.lokakarya.Dto.User.UserReqDto;
import com.example.lokakarya.Dto.User.UserReqUpdateDto;
import com.example.lokakarya.Dto.User.UserResetPassDto;

public interface UserServ {
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID id);
    UserDto createUser(UserReqDto userDto);
    UserDto updateUser(UUID id, UserReqUpdateDto userDto);
    Boolean deleteUser(UUID id);
    String resetPassword(UUID id, UserResetPassDto userDto);
}