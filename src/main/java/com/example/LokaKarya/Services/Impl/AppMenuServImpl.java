package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AppMenu.AppMenuByUserDto;
import com.example.LokaKarya.util.GetUserUtil;
import com.example.LokaKarya.Dto.AppMenu.AppMenuDto;
import com.example.LokaKarya.Dto.AppMenu.AppMenuReqDto;
import com.example.LokaKarya.Entity.AppMenu;
import com.example.LokaKarya.Repository.AppMenuRepo;
import com.example.LokaKarya.Services.AppMenuServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppMenuServImpl implements AppMenuServ {
    private final Logger Log = LoggerFactory.getLogger(AppMenuServ.class);

    @Autowired
    AppMenuRepo appMenuRepo;

    @Autowired
    GetUserUtil getUserUtil;

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
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        AppMenu appMenu = AppMenuDto.toEntity(appMenuDto, null, null, currentUserId, new java.util.Date());
        appMenuRepo.save(appMenu);
        return AppMenuReqDto.fromEntity(appMenu);
    }

    @Override
    public AppMenuReqDto updateAppMenu(UUID id, AppMenuDto appMenuDto) {
        AppMenu appMenu = appMenuRepo.findById(id).orElseThrow(() -> new RuntimeException("AppMenu not found"));
        UUID currentUserId = getUserUtil.getCurrentUser().getId();
        AppMenu appMenuUpdate = AppMenuDto.toEntity(appMenuDto, currentUserId, new java.util.Date(), appMenu.getCreatedBy() ,appMenu.getCreatedAt());
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

    @Override
    public List<AppMenuByUserDto> getAllAppMenuByUser(UUID id) {
        List<Object[]> menuList = appMenuRepo.getAppMenuByUserId(id);
        List<AppMenuByUserDto> appMenuByUserDto = new ArrayList<>();
        for (Object[] e : menuList) {
            AppMenuByUserDto appMenuByUserDto1 = new AppMenuByUserDto();
            appMenuByUserDto1.setId(bytesToUUID((byte[]) e[0]));
            appMenuByUserDto1.setMenuName((String) e[1]);
            appMenuByUserDto1.setRoleName((String) e[2]);
            appMenuByUserDto.add(appMenuByUserDto1);
        }
        return appMenuByUserDto;
    }

    private UUID bytesToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }
}
