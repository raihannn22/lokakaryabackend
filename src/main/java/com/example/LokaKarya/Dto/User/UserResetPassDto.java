package com.example.LokaKarya.Dto.User;

import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserResetPassDto {

    @ReadOnlyProperty
    @JsonIgnoreProperties
    private String password = "ogya123";

    public static User toEntity(UserResetPassDto userDto, UUID createdBy, Date createdAt , UUID updatedBy, Date updatedAt) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setCreatedBy(createdBy);
        user.setCreatedAt(createdAt);
        user.setUpdatedBy(updatedBy);
        user.setUpdatedAt(updatedAt);
        return user;
    }
}
