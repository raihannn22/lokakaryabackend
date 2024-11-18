package com.example.LokaKarya.Dto.AppUserRole;

import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Entity.AppUserRole;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppUserRoleReqDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("ROLE_ID")
    private AppRole appRole;

    @JsonProperty("USER_ID")
    private User user;

    public static AppUserRoleReqDto fromEntity(AppUserRole appUserRole) {
        AppUserRoleReqDto appUserRoleReqDto = new AppUserRoleReqDto();
        appUserRoleReqDto.setId(appUserRole.getId());
        appUserRoleReqDto.setAppRole(appUserRole.getAppRole());
        appUserRoleReqDto.setUser(appUserRole.getUser());
        return appUserRoleReqDto;
    }
}
