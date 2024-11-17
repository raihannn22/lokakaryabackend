package com.example.LokaKarya.Dto.AppMenu;

import com.example.LokaKarya.Entity.AppMenu;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppMenuReqDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("MENU_NAME")
    private String menuName;

    @JsonProperty("CREATED_AT")
    private Date createdAt;

    @JsonProperty("CREATED_BY")
    private UUID createdBy;

    @JsonProperty("UPDATED_BY")
    private UUID updatedBy;

    @JsonProperty("UPDATED_AT")
    private Date updatedAt;

    public static  AppMenuReqDto fromEntity(AppMenu appMenu) {
        AppMenuReqDto appMenuReqDto = new AppMenuReqDto();
        appMenuReqDto.setId(appMenu.getId());
        appMenuReqDto.setMenuName(appMenu.getMenuName());
        appMenuReqDto.setCreatedAt(appMenu.getCreatedAt());
        appMenuReqDto.setCreatedBy(appMenu.getCreatedBy());
        appMenuReqDto.setUpdatedBy(appMenu.getUpdatedBy());
        appMenuReqDto.setUpdatedAt(appMenu.getUpdatedAt());
        return appMenuReqDto;
    }
}
