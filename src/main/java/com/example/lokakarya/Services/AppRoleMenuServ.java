package com.example.lokakarya.Services;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.example.lokakarya.Dto.AppRoleMenu.AppRoleMenuReqDto;

public interface AppRoleMenuServ {
    List<AppRoleMenuReqDto> getAllAppRoleMenu();
    AppRoleMenuReqDto getAppRoleMenuById(UUID id);
    void createAppRoleMenu(Map<UUID, List<UUID>> appRoleMenuDto);
}