package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.AppRoleMenu.AppRoleMenuReqDto;
import com.example.lokakarya.Dto.AppUserRole.AppUserRoleReqDto;

public interface AppUserRoleServ {
    List<AppUserRoleReqDto> getAllAppUserRoleMenu();
    AppUserRoleReqDto getAppUserRoleById(UUID id);
}
