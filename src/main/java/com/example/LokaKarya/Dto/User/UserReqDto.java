package com.example.LokaKarya.Dto.User;

import com.example.LokaKarya.Entity.Division;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
//import ogya.workshop.performance_appraisal.entity.User;?
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserReqDto {

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
    @JsonProperty("password")
    private String password;
    @JsonProperty("division_id")
    private UUID division;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;
    @JsonProperty("updated_at")
    private Date updatedAt;

    public static User toEntity(UserReqDto userDto, UUID createdBy,Date createdAt ,UUID updatedBy, Date updatedAt) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setPosition(userDto.getPosition());
        user.setEmail(userDto.getEmailAddress());
        user.setEmployeeStatus(userDto.getEmployeeStatus());
        user.setJoinDate(userDto.getJoinDate() != null ? java.sql.Date.valueOf(userDto.getJoinDate().toLocalDate()) : null);
        user.setEnabled(userDto.getEnabled());
        user.setPassword(userDto.getPassword());
        user.setCreatedBy(createdBy);
        user.setCreatedAt(createdAt);
        user.setUpdatedBy(updatedBy);
        user.setUpdatedAt(updatedAt);
        return user;
    }

    public static User toEntity2(UserReqDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setPosition(userDto.getPosition());
        user.setEmail(userDto.getEmailAddress());
        user.setEmployeeStatus(userDto.getEmployeeStatus());
        user.setJoinDate(userDto.getJoinDate() != null ? java.sql.Date.valueOf(userDto.getJoinDate().toLocalDate()) : null);
        user.setEnabled(userDto.getEnabled());
        user.setPassword(userDto.getPassword());
        return user;
    }

//    public static UserReqDto fromEntity(User user) {
//        UserReqDto userDto = new UserReqDto();
//        userDto.setUsername(user.getUsername());
//        userDto.setFullName(user.getFullName());
//        userDto.setPosition(user.getPosition());
//        userDto.setEmailAddress(user.getEmail());
//        userDto.setEmployeeStatus(user.getEmployeeStatus());
//        userDto.setDivision(user.getDivision().getId());
//        userDto.setJoinDate(Date.valueOf(user.getJoinDate().toLocalDate()));
//        userDto.setEnabled(user.getEnabled());
//        userDto.setPassword(user.getPassword());
//        userDto.setDivision(user.getDivision().getId());
//        return userDto;
//    }
}
