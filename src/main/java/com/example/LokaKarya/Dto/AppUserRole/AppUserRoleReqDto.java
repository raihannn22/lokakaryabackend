package com.example.LokaKarya.Dto.AppUserRole;

import com.example.LokaKarya.Dto.AppRole.AppRoleReqDto;
import com.example.LokaKarya.Dto.User.UserDto;
import com.example.LokaKarya.Dto.User.UserReqDto;
import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Entity.AppUserRole;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private AppRoleReqDto appRole;

    @JsonProperty("USER_ID")
    private UserDto user;

    public static AppUserRoleReqDto fromEntity(AppUserRole appUserRole) {
        AppUserRoleReqDto appUserRoleReqDto = new AppUserRoleReqDto();
        appUserRoleReqDto.setId(appUserRole.getId());
        appUserRoleReqDto.setAppRole(AppRoleReqDto.fromEntity(appUserRole.getAppRole()));
        appUserRoleReqDto.setUser(UserDto.fromEntity(appUserRole.getUser()));
        return appUserRoleReqDto;
    }
}
