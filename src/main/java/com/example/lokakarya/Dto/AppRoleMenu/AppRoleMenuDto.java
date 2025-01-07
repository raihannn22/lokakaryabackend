package com.example.lokakarya.Dto.AppRoleMenu;
import com.example.lokakarya.Entity.AppRoleMenu;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Set;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppRoleMenuDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("ROLE_ID")
    private UUID appRole;

    @JsonProperty("MENU_ID")
    private Set<UUID> appMenu;

        public static AppRoleMenu toEntity(AppRoleMenuDto appRoleMenuDto) {
        AppRoleMenu appRoleMenu = new AppRoleMenu();
        appRoleMenu.setId(appRoleMenuDto.getId());
        return appRoleMenu;
    }
}