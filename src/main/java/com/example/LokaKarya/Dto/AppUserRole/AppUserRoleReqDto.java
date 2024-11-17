package com.example.LokaKarya.Dto.AppUserRole;

import com.example.LokaKarya.Entity.AppRole;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

public class AppUserRoleReqDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("ROLE_ID")
    private AppRole appRole;

    @JsonProperty("USER_ID")
    private User user;
}
