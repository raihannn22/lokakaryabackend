package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.AppUserRole.AppUserRoleReqDto;

import java.util.List;
import java.util.UUID;

public interface AppUserRoleServ {
    List<AppUserRoleReqDto> getAllAppUserRoleMenu();
    AppUserRoleReqDto getAppUserRoleById(UUID id);
}
