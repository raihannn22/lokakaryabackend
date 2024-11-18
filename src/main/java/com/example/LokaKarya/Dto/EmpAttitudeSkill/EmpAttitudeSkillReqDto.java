package com.example.LokaKarya.Dto.EmpAttitudeSkill;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
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
public class EmpAttitudeSkillReqDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("attitude_skill_id")
    private UUID attitudeSkillId;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;

    public static EmpAttitudeSkill toEntity(EmpAttitudeSkillReqDto empAttitudeSkillDto) {
        EmpAttitudeSkill empAttitudeSkill = new EmpAttitudeSkill();
        empAttitudeSkill.setUserId(empAttitudeSkillDto.getUserId());
        empAttitudeSkill.setAttitudeSkillId(empAttitudeSkillDto.getAttitudeSkillId());
        empAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        empAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        return empAttitudeSkill;
    }
}

