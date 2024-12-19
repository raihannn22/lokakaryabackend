package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuDto;
import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuReqDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AppRoleMenuServ {
    List<AppRoleMenuReqDto> getAllAppRoleMenu();
    AppRoleMenuReqDto getAppRoleMenuById(UUID id);
     void createAppRoleMenu(Map<UUID, List<UUID>> appRoleMenuDto);
}
