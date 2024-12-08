package com.example.LokaKarya.Dto.AppRoleMenu;


import com.example.LokaKarya.Entity.AppMenu;
import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Entity.AppRoleMenu;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppRoleMenuReqDto {

    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("ROLE_ID")
    private AppRole appRole;

    @JsonProperty("MENU_ID")
    private AppMenu appMenu;


    public static AppRoleMenuReqDto fromEntity(AppRoleMenu appRoleMenu) {
        AppRoleMenuReqDto appRoleMenuReqDto = new AppRoleMenuReqDto();
        appRoleMenuReqDto.setId(appRoleMenu.getId());
        appRoleMenuReqDto.setAppRole(appRoleMenu.getAppRole());
        appRoleMenuReqDto.setAppMenu(appRoleMenu.getAppMenu());
        return appRoleMenuReqDto;
    }
}
