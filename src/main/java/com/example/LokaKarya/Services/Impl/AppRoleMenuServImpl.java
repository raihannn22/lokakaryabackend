package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AppRoleMenu.AppRoleMenuReqDto;
import com.example.LokaKarya.Entity.AppRoleMenu;
import com.example.LokaKarya.Repository.AppRoleMenuRepo;
import com.example.LokaKarya.Services.AppMenuServ;
import com.example.LokaKarya.Services.AppRoleMenuServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppRoleMenuServImpl implements AppRoleMenuServ {
    private final Logger Log = LoggerFactory.getLogger(AppRoleMenuServImpl.class);
    @Autowired
    private AppRoleMenuRepo appRoleMenuRepo;

    @Override
    public List<AppRoleMenuReqDto> getAllAppRoleMenu() {
        List<AppRoleMenu> response = appRoleMenuRepo.findAll();
        List<AppRoleMenuReqDto> appRoleMenuReqDto = new ArrayList<>();
        for (AppRoleMenu appRoleMenu : response) {
            appRoleMenuReqDto.add(AppRoleMenuReqDto.fromEntity(appRoleMenu));
        }
        return appRoleMenuReqDto;
    }

    @Override
    public AppRoleMenuReqDto getAppRoleMenuById(UUID id) {
        AppRoleMenu appRoleMenu = appRoleMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRoleMenu not found"));
        return AppRoleMenuReqDto.fromEntity(appRoleMenu);
    }
}
