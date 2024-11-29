package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Dto.User.UserReqDto;

public interface UserServ {
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID id);
    UserDto createUser(UserReqDto userDto);
    UserDto updateUser(UUID id, UserReqDto userDto);
    Boolean deleteUser(UUID id);
}