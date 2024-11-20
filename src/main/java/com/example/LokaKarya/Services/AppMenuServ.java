package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.AppMenu.AppMenuDto;
import com.example.LokaKarya.Dto.AppMenu.AppMenuReqDto;

import java.util.List;
import java.util.UUID;

public interface AppMenuServ {
    List<AppMenuReqDto> getAllAppMenu();
    AppMenuReqDto getAppMenuById(UUID id);
    AppMenuReqDto createAppMenu(AppMenuDto appMenuDto);
    AppMenuReqDto updateAppMenu(UUID id, AppMenuDto appMenuDto);
    Boolean deleteAppMenu(UUID id);
}
