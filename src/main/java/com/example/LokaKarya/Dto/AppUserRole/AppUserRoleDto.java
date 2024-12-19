package com.example.lokakarya.Dto.AppUserRole;

import com.example.lokakarya.Dto.AppRole.AppRoleReqDto;
import com.example.lokakarya.Dto.User.UserDto;
import com.example.lokakarya.Entity.AppRole;
import com.example.lokakarya.Entity.AppUserRole;
import com.example.lokakarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppUserRoleDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("ROLE_ID")
    private AppRole appRole;

    @JsonProperty("USER_ID")
    private User user;

    public static AppUserRoleReqDto fromEntity(AppUserRole appUserRole) {
        AppUserRoleReqDto appUserRoleReqDto = new AppUserRoleReqDto();
        appUserRoleReqDto.setId(appUserRole.getId());
        appUserRoleReqDto.setAppRole(AppRoleReqDto.fromEntity(appUserRole.getAppRole()));
        appUserRoleReqDto.setUser(UserDto.fromEntity(appUserRole.getUser()));
        return appUserRoleReqDto;
    }
}
