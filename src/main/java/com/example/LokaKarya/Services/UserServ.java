package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.AppUserRole.AppUserRoleReqDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Dto.User.UserReqDto;
import com.example.LokaKarya.Dto.User.UserReqUpdateDto;
import com.example.LokaKarya.Dto.User.UserResetPassDto;

public interface UserServ {
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID id);
    UserDto createUser(UserReqDto userDto);
    UserDto updateUser(UUID id, UserReqUpdateDto userDto);
    Boolean deleteUser(UUID id);
    UserDto resetPassword(UUID id, UserResetPassDto userDto);
}