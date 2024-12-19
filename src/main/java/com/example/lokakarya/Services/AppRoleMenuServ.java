package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.AppRoleMenu.AppRoleMenuReqDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AppRoleMenuServ {
    List<AppRoleMenuReqDto> getAllAppRoleMenu();
    AppRoleMenuReqDto getAppRoleMenuById(UUID id);
     void createAppRoleMenu(Map<UUID, List<UUID>> appRoleMenuDto);
//    AppMenuReqDto updateAppMenu(UUID id, AppMenuDto appMenuDto);
//    Boolean deleteAppMenu(UUID id);
}
