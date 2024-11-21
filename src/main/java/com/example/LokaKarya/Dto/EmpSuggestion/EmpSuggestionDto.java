package com.example.LokaKarya.Dto.EmpSuggestion;

import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.EmpSuggestion;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
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


    
    public static EmpSuggestion toEntity(EmpSuggestionDto empSuggestionDto, User user, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        EmpSuggestion empSuggestion = new EmpSuggestion();
        empSuggestion.setId(empSuggestionDto.getId());
        empSuggestion.setUser(user);
        empSuggestion.setSuggestion(empSuggestionDto.getSuggestion());
        empSuggestion.setAssessmentYear(empSuggestionDto.getAssessmentYear());
        empSuggestion.setCreatedAt(createdAt);
        empSuggestion.setCreatedBy(createdBy);
        empSuggestion.setUpdatedAt(updateAt);
        empSuggestion.setUpdatedBy(updateBy);
        return empSuggestion;
    }


    // public static EmpSuggestionDto fromEntity(EmpSuggestion empSuggestion) {
    //     EmpSuggestionDto empSuggestionDto = new EmpSuggestionDto();
    //     empSuggestionDto.setId(empSuggestion.getId());
    //     empSuggestionDto.setUserId(empSuggestion.getUserId());
    //     empSuggestionDto.setSuggestion(empSuggestion.getSuggestion());
    //     empSuggestionDto.setAssessmentYear(empSuggestion.getAssessmentYear());
    //     return empSuggestionDto;
    // }
}

