package com.example.LokaKarya.Dto.EmpSuggestion;

import com.example.LokaKarya.Entity.EmpSuggestion;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpSuggestionDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("suggestion")
    private String suggestion;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;


    public static EmpSuggestionDto fromEntity(EmpSuggestion empSuggestion) {
        EmpSuggestionDto empSuggestionDto = new EmpSuggestionDto();
        empSuggestionDto.setId(empSuggestion.getId());
        empSuggestionDto.setUserId(empSuggestion.getUserId());
        empSuggestionDto.setSuggestion(empSuggestion.getSuggestion());
        empSuggestionDto.setAssessmentYear(empSuggestion.getAssessmentYear());
        return empSuggestionDto;
    }

    public static EmpSuggestion toEntity(EmpSuggestionDto empSuggestionDto) {
        EmpSuggestion empSuggestion = new EmpSuggestion();
        empSuggestion.setId(empSuggestionDto.getId());
        empSuggestion.setUserId(empSuggestionDto.getUserId());
        empSuggestion.setSuggestion(empSuggestionDto.getSuggestion());
        empSuggestion.setAssessmentYear(empSuggestionDto.getAssessmentYear());
        return empSuggestion;
    }
}

