package com.example.lokakarya.Dto.AppRole;

import com.example.lokakarya.Entity.AppRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppRoleReqDto {
    @JsonProperty("ID")
    private UUID id;
    @JsonProperty("ROLENAME")
    private String roleName;
    @JsonProperty("CREATED_AT")
    private Date createdAt;
    @JsonProperty("CREATED_BY")
    private UUID createdBy;
    @JsonProperty("UPDATED_AT")
    private Date updatedAt;
    @JsonProperty("UPDATED_BY")
    private UUID updatedBy;

    public static AppRoleReqDto fromEntity( AppRole appRole) {
        AppRoleReqDto appRoleReqDto = new AppRoleReqDto();
        appRoleReqDto.setId(appRole.getId());
        appRoleReqDto.setRoleName(appRole.getRoleName());
        appRoleReqDto.setCreatedAt(appRole.getCreatedAt());
        appRoleReqDto.setCreatedBy(appRole.getCreatedBy());
        appRoleReqDto.setUpdatedAt(appRole.getUpdatedAt());
        appRoleReqDto.setUpdatedBy(appRole.getUpdatedBy());
        return appRoleReqDto;
    }
}
