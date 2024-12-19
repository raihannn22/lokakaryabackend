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
public class AppMenuByUserDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("MENU_NAME")
    private String menuName;

    @JsonProperty("ROLE_NAME")
    private String roleName;

    public static AppMenuByUserDto fromEntity(AppMenu appMenu) {
        return AppMenuByUserDto.builder()
                .id(appMenu.getId())
                .menuName(appMenu.getMenuName())
                .build();
    }
}
