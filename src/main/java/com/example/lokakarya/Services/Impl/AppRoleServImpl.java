package com.example.lokakarya.Services.Impl;

import com.example.lokakarya.util.GetUserUtil;
import com.example.lokakarya.Dto.AppRole.AppRoleDto;
import com.example.lokakarya.Dto.AppRole.AppRoleReqDto;
import com.example.lokakarya.Entity.AppRole;
import com.example.lokakarya.Repository.AppRoleRepo;
import com.example.lokakarya.Services.AppRoleServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class AppRoleServImpl implements AppRoleServ {
    @Autowired
    private AppRoleRepo appRoleRepo;

    @Autowired
    private GetUserUtil getUserUtil;

    private final Logger Log = LoggerFactory.getLogger(AppRoleServImpl.class);

    @Override
    public List<AppRoleReqDto> getAllAppRole() {
        Log.info("Start getAllAppRole in AppRoleServImpl");
        List<AppRole> response = appRoleRepo.findAll();
        List<AppRoleReqDto> appRoleReqDto = new ArrayList<>();
        for (AppRole appRole : response) {
            appRoleReqDto.add(AppRoleReqDto.fromEntity(appRole));
        }
        Log.info("End getAllAppRole in AppRoleServImpl");
        return appRoleReqDto;
    }

    @Override
    public AppRoleReqDto getAppRoleById(UUID id) {
        Log.info("Start getAppRoleById in AppRoleServImpl");
        AppRole appRole = appRoleRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRole not found"));
        Log.info("End getAppRoleById in AppRoleServImpl");
        return AppRoleReqDto.fromEntity(appRole);
    }

    @Override
    public AppRoleReqDto createAppRole(AppRoleDto appRoleDto) {
        Log.info("Start createAppRole in AppRoleServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        AppRole appRole = AppRoleDto.toEntity(appRoleDto, null, null, currentUser,new java.util.Date());
        appRoleRepo.save(appRole);
        Log.info("End createAppRole in AppRoleServImpl");
        return AppRoleReqDto.fromEntity(appRole);
    }

    @Override
    public AppRoleReqDto updateAppRole(UUID id, AppRoleDto appRoleDto) {
        Log.info("Start updateAppRole in AppRoleServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        AppRole appRole = appRoleRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRole not found"));
        AppRole appRole1 = AppRoleDto.toEntity(appRoleDto, currentUser, new java.util.Date(), appRole.getCreatedBy(),appRole.getCreatedAt());
        appRole1.setId(id);
        appRoleRepo.save(appRole1);
        Log.info("End updateAppRole in AppRoleServImpl");
        return AppRoleReqDto.fromEntity(appRole1);
    }

    @Override
    public Boolean deleteAppRole(UUID id) {
        Log.info("Start deleteAppRole in AppRoleServImpl");
        AppRole appRole = appRoleRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRole not found"));
        appRoleRepo.delete(appRole);
        Log.info("End deleteAppRole in AppRoleServImpl");
        return true;
    }
}
