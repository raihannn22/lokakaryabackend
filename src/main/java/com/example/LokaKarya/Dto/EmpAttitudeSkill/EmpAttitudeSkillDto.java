package com.example.LokaKarya.Dto.EmpAttitudeSkill;

import com.example.LokaKarya.Entity.EmpAttitudeSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("attitude_skill_id")
    private UUID attitudeSkillId;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;

    public static EmpAttitudeSkillDto fromEntity(EmpAttitudeSkill empAttitudeSkill) {
        EmpAttitudeSkillDto empAttitudeSkillDto = new EmpAttitudeSkillDto();
        empAttitudeSkillDto.setId(empAttitudeSkill.getId());
        empAttitudeSkillDto.setUserId(empAttitudeSkill.getUserId());
        empAttitudeSkillDto.setAttitudeSkillId(empAttitudeSkill.getAttitudeSkillId());
        empAttitudeSkillDto.setScore(empAttitudeSkill.getScore());
        empAttitudeSkillDto.setAssessmentYear(empAttitudeSkill.getAssessmentYear());
        return empAttitudeSkillDto;
    }

    public static EmpAttitudeSkill toEntity(EmpAttitudeSkillDto empAttitudeSkillDto) {
        EmpAttitudeSkill empAttitudeSkill = new EmpAttitudeSkill();
        empAttitudeSkill.setId(empAttitudeSkillDto.getId());
        empAttitudeSkill.setUserId(empAttitudeSkillDto.getUserId());
        empAttitudeSkill.setAttitudeSkillId(empAttitudeSkillDto.getAttitudeSkillId());
        empAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        empAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        return empAttitudeSkill;
    }
}

