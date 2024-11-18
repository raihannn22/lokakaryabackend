package com.example.LokaKarya.Dto.User;

import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Entity.Division;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.DataInput;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserDto {
    @JsonProperty("id")
    private UUID id;
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
    @JsonProperty("join_date")
    private Date joinDate;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("password")
    private String password;
    @JsonProperty("division_id")
    private Division division;


    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setPosition(user.getPosition());
        userDto.setEmailAddress(user.getEmail());
        userDto.setEmployeeStatus(user.getEmployeeStatus());
        userDto.setJoinDate(Date.valueOf(user.getJoinDate().toLocalDate()));
        userDto.setEnabled(user.getEnabled());
        userDto.setPassword(user.getPassword());
        userDto.setDivision(user.getDivision());
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setPosition(userDto.getPosition());
        user.setEmail(userDto.getEmailAddress());
        user.setEmployeeStatus(userDto.getEmployeeStatus());
        user.setJoinDate(userDto.getJoinDate() != null ? Date.valueOf((userDto.getJoinDate().toLocalDate())) : null);
        user.setEnabled(userDto.getEnabled());
        user.setPassword(userDto.getPassword());
        user.setDivision(userDto.getDivision());
        return user;
    }
}
