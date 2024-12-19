package com.example.lokakarya.Dto.EmpSuggestion;

import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.lokakarya.Dto.EmpSuggestion.EmpSuggestionReqDto;
import com.example.lokakarya.Entity.EmpSuggestion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpSuggestionReqDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("suggestion")
    private String suggestion;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;


    public static EmpSuggestionReqDto fromEntity(EmpSuggestion empSuggestion) {
        EmpSuggestionReqDto empSuggestionReqDto = new EmpSuggestionReqDto();
        empSuggestionReqDto.setUserId(empSuggestion.getUser().getId());
        empSuggestionReqDto.setSuggestion(empSuggestion.getSuggestion());
        empSuggestionReqDto.setAssessmentYear(empSuggestion.getAssessmentYear());
        return empSuggestionReqDto;
    }
}

