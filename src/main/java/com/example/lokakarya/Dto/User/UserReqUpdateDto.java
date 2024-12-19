package com.example.lokakarya.Dto.User;

import com.example.lokakarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserReqUpdateDto {

    @JsonProperty("username")
    private String username;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("position")
    private String position;
    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("employee_status")
    private Integer employeeStatus;
    @JsonProperty ("app_role")
    private Set<UUID> appRole;
    @JsonProperty("join_date")
    private java.sql.Date joinDate;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("division_id")
    private UUID division;

    public static User toEntity(UserReqUpdateDto userDto, UUID createdBy, Date createdAt , UUID updatedBy, Date updatedAt) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setPosition(userDto.getPosition());
        user.setEmail(userDto.getEmailAddress());
        user.setEmployeeStatus(userDto.getEmployeeStatus());
        user.setJoinDate(userDto.getJoinDate() != null ? java.sql.Date.valueOf(userDto.getJoinDate().toLocalDate()) : null);
        user.setEnabled(userDto.getEnabled());
        user.setCreatedBy(createdBy);
        user.setCreatedAt(createdAt);
        user.setUpdatedBy(updatedBy);
        user.setUpdatedAt(updatedAt);
        return user;
    }
}
