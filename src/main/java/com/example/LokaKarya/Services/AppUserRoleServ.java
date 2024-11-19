package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuReqDto;
import com.example.LokaKarya.Dto.AppUserRole.AppUserRoleReqDto;

import java.util.List;
import java.util.UUID;

public interface AppUserRoleServ {
    List<AppUserRoleReqDto> getAllAppUserRoleMenu();
    AppUserRoleReqDto getAppUserRoleById(UUID id);
}
