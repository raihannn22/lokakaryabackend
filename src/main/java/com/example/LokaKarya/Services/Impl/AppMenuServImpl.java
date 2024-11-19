package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AppMenu.AppMenuDto;
import com.example.LokaKarya.Dto.AppMenu.AppMenuReqDto;
import com.example.LokaKarya.Entity.AppMenu;
import com.example.LokaKarya.Repository.AppMenuRepo;
import com.example.LokaKarya.Services.AppMenuServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppMenuServImpl implements AppMenuServ {
    private final Logger Log = LoggerFactory.getLogger(AppMenuServ.class);

    @Autowired
    AppMenuRepo appMenuRepo;

    @Override
    public List<AppMenuReqDto> getAllAppMenu() {
        List<AppMenu> response = appMenuRepo.findAll();
        List<AppMenuReqDto> appMenuReqDto = new ArrayList<>();
        for (AppMenu appMenu : response) {
            appMenuReqDto.add(AppMenuReqDto.fromEntity(appMenu));
        }
        return appMenuReqDto;
    }

    @Override
    public AppMenuReqDto getAppMenuById(UUID id) {
       AppMenu appMenu = appMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppMenu not found"));
       return AppMenuReqDto.fromEntity(appMenu);
    }

    @Override
    public AppMenuReqDto createAppMenu(AppMenuDto appMenuDto) {
        AppMenu appMenu = AppMenuDto.toEntity(appMenuDto, null, null, UUID.randomUUID(), new java.util.Date());
        appMenuRepo.save(appMenu);
        return AppMenuReqDto.fromEntity(appMenu);
    }

    @Override
    public AppMenuReqDto updateAppMenu(UUID id, AppMenuDto appMenuDto) {
        AppMenu appMenu = appMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppMenu not found"));
        AppMenu appMenuUpdate = AppMenuDto.toEntity(appMenuDto, appMenu.getUpdatedBy(), new java.util.Date(), appMenu.getCreatedBy() ,appMenu.getCreatedAt());
        appMenuUpdate.setId(id);
        appMenuRepo.save(appMenuUpdate);
        return AppMenuReqDto.fromEntity(appMenu);
    }

    @Override
    public Boolean deleteAppMenu(UUID id) {
        AppMenu appMenu = appMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppMenu not found"));
        appMenuRepo.delete(appMenu);
        return true;
    }
}
