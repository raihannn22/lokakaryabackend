package com.example.LokaKarya.Dto.EmpTechnicalSkill;

import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpTechnicalSkillReqDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("technical_skill_id")
    private UUID technicalSkillId;
    @JsonProperty("criteria")
    private String criteria;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;


    public static EmpTechnicalSkillReqDto fromEntity(EmpTechnicalSkill empTechnicalSkill) {
        EmpTechnicalSkillReqDto empTechnicalSkillReqDto = new EmpTechnicalSkillReqDto();
        empTechnicalSkillReqDto.setUserId(empTechnicalSkill.getUser().getId());
        empTechnicalSkillReqDto.setTechnicalSkillId(empTechnicalSkill.getTechnicalSkill().getId());
        empTechnicalSkillReqDto.setScore(empTechnicalSkill.getScore());
        empTechnicalSkillReqDto.setCriteria(empTechnicalSkill.getCriteria());
        empTechnicalSkillReqDto.setAssessmentYear(empTechnicalSkill.getAssessmentYear());
        return empTechnicalSkillReqDto;
    }
}

