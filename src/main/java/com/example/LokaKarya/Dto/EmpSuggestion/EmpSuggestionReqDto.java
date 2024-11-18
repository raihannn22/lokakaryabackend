package com.example.LokaKarya.Dto.EmpSuggestion;

import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionReqDto;
import com.example.LokaKarya.Entity.EmpSuggestion;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpSuggestionReqDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("suggestion")
    private String suggestion;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;


    public static EmpSuggestion toEntity(EmpSuggestionReqDto empSuggestionDto) {
        EmpSuggestion empSuggestion = new EmpSuggestion();
        empSuggestion.setUserId(empSuggestionDto.getUserId());
        empSuggestion.setSuggestion(empSuggestionDto.getSuggestion());
        empSuggestion.setAssessmentYear(empSuggestionDto.getAssessmentYear());
        return empSuggestion;
    }
}

