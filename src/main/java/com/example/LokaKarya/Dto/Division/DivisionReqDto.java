package com.example.LokaKarya.Dto.Division;

import com.example.LokaKarya.Entity.Division;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DivisionReqDto {
    @JsonProperty("ID")
    private UUID id;

    @JsonProperty("DIVISION_NAME")
    private String divisionName;

    @JsonProperty("CREATED_BY")
    private UUID createdBy;

    @JsonProperty("CREATED_AT")
    private Date createdAt;

    @JsonProperty("UPDATED_BY")
    private UUID updatedBy;

    @JsonProperty("UPDATED_AT")
    private Date updatedAt;

    public static DivisionReqDto fromEntity(Division division) {
        DivisionReqDto divisionReqDto = new DivisionReqDto();
        divisionReqDto.setId(division.getId());
        divisionReqDto.setDivisionName(division.getDivisionName());
        divisionReqDto.setCreatedBy(division.getCreatedBy());
        divisionReqDto.setCreatedAt(division.getCreatedAt());
        divisionReqDto.setUpdatedBy(division.getUpdatedBy());
        divisionReqDto.setUpdatedAt(division.getUpdatedAt());
        return divisionReqDto;
    }
}
