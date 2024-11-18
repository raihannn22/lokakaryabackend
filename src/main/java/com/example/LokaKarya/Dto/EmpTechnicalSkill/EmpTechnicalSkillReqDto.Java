package com.example.LokaKarya.Dto.EmpTechnicalSkill;

import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpTechnicalSkillReqDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("technical_skill_id")
    private UUID technicalSkillId;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;


    public static EmpTechnicalSkill toEntity(EmpTechnicalSkillReqDto empTechnicalSkillDto) {
        EmpTechnicalSkill empTechnicalSkill = new EmpTechnicalSkill();
        empTechnicalSkill.setUserId(empTechnicalSkillDto.getUserId());
        empTechnicalSkill.setTechnicalSkillId(empTechnicalSkillDto.getTechnicalSkillId());
        empTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
        empTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
        return empTechnicalSkill;
    }
}

