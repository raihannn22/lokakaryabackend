package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AppMenu.AppMenuDto;
import com.example.LokaKarya.Dto.AppMenu.AppMenuReqDto;
import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuReqDto;

import java.util.List;
import java.util.UUID;

public interface AppRoleMenuServ {
    List<AppRoleMenuReqDto> getAllAppRoleMenu();
    AppRoleMenuReqDto getAppRoleMenuById(UUID id);
//    AppMenuReqDto createAppMenu(AppMenuDto appMenuDto);
//    AppMenuReqDto updateAppMenu(UUID id, AppMenuDto appMenuDto);
//    Boolean deleteAppMenu(UUID id);
}
