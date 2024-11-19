package com.example.LokaKarya.Dto.AppMenu;

import com.example.LokaKarya.Entity.AppMenu;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AppMenuDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("MENU_NAME")
    private String menuName;

    public static AppMenu toEntity(AppMenuDto dto, UUID updatedBy, Date updatedAt, UUID createdBy) {
        AppMenu appMenu = new AppMenu();
        appMenu.setId(dto.getId());
        appMenu.setMenuName(dto.getMenuName());
        appMenu.setCreatedBy(updatedBy);
        appMenu.setUpdatedAt(updatedAt);
        appMenu.setUpdatedBy(createdBy);
        return appMenu;
    }
}
