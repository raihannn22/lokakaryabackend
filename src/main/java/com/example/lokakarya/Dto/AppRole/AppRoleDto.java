package com.example.lokakarya.Dto.AppRole;
import com.example.lokakarya.Entity.AppRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppRoleDto {
    @JsonProperty("ID")
    private UUID id;
    @JsonProperty("ROLENAME")
    private String roleName;

    public static AppRole toEntity(AppRoleDto appRoleDto, UUID updatedBy, Date updatedAt, UUID createdBy, Date createdAt) {
        AppRole appRole = new AppRole();
        appRole.setId(appRoleDto.getId());
        appRole.setRoleName(appRoleDto.getRoleName());
        appRole.setUpdatedAt(updatedAt);
        appRole.setUpdatedBy(updatedBy);
        appRole.setCreatedBy(createdBy);
        appRole.setCreatedAt(createdAt);
        return appRole;
    }
}