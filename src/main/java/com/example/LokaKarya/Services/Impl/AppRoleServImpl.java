package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AppRole.AppRoleDto;
import com.example.LokaKarya.Dto.AppRole.AppRoleReqDto;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Repository.AppRoleRepo;
import com.example.LokaKarya.Services.AppRoleServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppRoleServImpl implements AppRoleServ {
    @Autowired
    private AppRoleRepo appRoleRepo;

    @Override
    public List<AppRoleReqDto> getAllAppRole() {
        List<AppRole> response = appRoleRepo.findAll();
        List<AppRoleReqDto> appRoleReqDto = new ArrayList<>();
        for (AppRole appRole : response) {
            appRoleReqDto.add(AppRoleReqDto.fromEntity(appRole));
        }
        return appRoleReqDto;
    }

    @Override
    public AppRoleReqDto getAppRoleById(UUID id) {
        AppRole appRole = appRoleRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRole not found"));
        return AppRoleReqDto.fromEntity(appRole);
    }

    @Override
    public AppRoleReqDto createAppRole(AppRoleDto appRoleDto) {
        AppRole appRole = AppRoleDto.toEntity(appRoleDto, UUID.randomUUID(), Date.valueOf(LocalDate.now()), UUID.randomUUID());
        appRoleRepo.save(appRole);
        return AppRoleReqDto.fromEntity(appRole);
    }

    @Override
    public AppRoleReqDto updateAppRole(UUID id, AppRoleDto appRoleDto) {
        return null;
    }

    @Override
    public Boolean deleteAppRole(UUID id) {
        AppRole appRole = appRoleRepo.findById(id).orElseThrow(() -> new RuntimeException("AppRole not found"));
        appRoleRepo.delete(appRole);
        return true;
    }
}
