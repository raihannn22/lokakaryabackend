package com.example.lokakarya.Dto.AppRoleMenu;
import com.example.lokakarya.Entity.AppMenu;
import com.example.lokakarya.Entity.AppRole;
import com.example.lokakarya.Entity.AppRoleMenu;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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