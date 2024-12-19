package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.AppMenu.AppMenuByUserDto;
import com.example.lokakarya.Dto.AppMenu.AppMenuDto;
import com.example.lokakarya.Dto.AppMenu.AppMenuReqDto;


public interface AppMenuServ {
    List<AppMenuReqDto> getAllAppMenu();
    AppMenuReqDto getAppMenuById(UUID id);
    AppMenuReqDto createAppMenu(AppMenuDto appMenuDto);
    AppMenuReqDto updateAppMenu(UUID id, AppMenuDto appMenuDto);
    Boolean deleteAppMenu(UUID id);
    List<AppMenuByUserDto> getAllAppMenuByUser(UUID id);
    List<AppMenuByUserDto> getAllAppMenuByUsername(String username);
}
