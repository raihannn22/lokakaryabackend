package com.example.LokaKarya.Dto.EmpTechnicalSkill;

import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpTechnicalSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("technical_skill_id")
    private UUID technicalSkillId;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;


    public static EmpTechnicalSkillDto fromEntity(EmpTechnicalSkill empTechnicalSkill) {
        EmpTechnicalSkillDto empTechnicalSkillDto = new EmpTechnicalSkillDto();
        empTechnicalSkillDto.setId(empTechnicalSkill.getId());
        empTechnicalSkillDto.setUserId(empTechnicalSkill.getUserId());
        empTechnicalSkillDto.setTechnicalSkillId(empTechnicalSkill.getTechnicalSkillId());
        empTechnicalSkillDto.setScore(empTechnicalSkill.getScore());
        empTechnicalSkillDto.setAssessmentYear(empTechnicalSkill.getAssessmentYear());
        return empTechnicalSkillDto;
    }

    public static EmpTechnicalSkill toEntity(EmpTechnicalSkillDto empTechnicalSkillDto) {
        EmpTechnicalSkill empTechnicalSkill = new EmpTechnicalSkill();
        empTechnicalSkill.setId(empTechnicalSkillDto.getId());
        empTechnicalSkill.setUserId(empTechnicalSkillDto.getUserId());
        empTechnicalSkill.setTechnicalSkillId(empTechnicalSkillDto.getTechnicalSkillId());
        empTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
        empTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
        return empTechnicalSkill;
    }
}

