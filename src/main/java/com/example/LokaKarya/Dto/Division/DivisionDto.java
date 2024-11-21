package com.example.LokaKarya.Dto.Division;

import com.example.LokaKarya.Entity.Division;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DivisionDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("DIVISION_NAME")
    private String divisionName;

    public static Division toEntity(DivisionDto divisionDto, UUID updatedBy, Date updatedAt, UUID createdBy, Date createdAt) {
        Division division = new Division();
        division.setId(divisionDto.getId());
        division.setDivisionName(divisionDto.getDivisionName());
        division.setUpdatedBy(updatedBy);
        division.setUpdatedAt(updatedAt);
        division.setCreatedBy(createdBy);
        division.setCreatedAt(createdAt);
        return division;
    }
}
