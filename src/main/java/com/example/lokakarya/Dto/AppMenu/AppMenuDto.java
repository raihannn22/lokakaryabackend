package com.example.lokakarya.Dto.AppMenu;
import com.example.lokakarya.Entity.AppMenu;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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

    public static AppMenu toEntity(AppMenuDto dto, UUID updatedBy, java.util.Date updatedAt, UUID createdBy, java.util.Date createdAt) {
        AppMenu appMenu = new AppMenu();
        appMenu.setId(dto.getId());
        appMenu.setMenuName(dto.getMenuName());
        appMenu.setUpdatedBy(updatedBy);
        appMenu.setUpdatedAt(updatedAt);
        appMenu.setCreatedBy(createdBy);
        appMenu.setCreatedAt(createdAt);
        return appMenu;
    }
}