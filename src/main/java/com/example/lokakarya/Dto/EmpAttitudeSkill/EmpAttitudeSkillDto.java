package com.example.lokakarya.Dto.EmpAttitudeSkill;

import com.example.lokakarya.Entity.EmpAttitudeSkill;
import com.example.lokakarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.Date;
import java.util.UUID;

import com.example.lokakarya.Entity.AttitudeSkill;

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

    
    public static EmpAttitudeSkill toEntity(EmpAttitudeSkillDto empAttitudeSkillDto, AttitudeSkill attitudeSkill, User user, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        EmpAttitudeSkill empAttitudeSkill = new EmpAttitudeSkill();
        empAttitudeSkill.setId(empAttitudeSkillDto.getId());
        empAttitudeSkill.setUser(user);
        empAttitudeSkill.setAttitudeSkill(attitudeSkill);
        empAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        empAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        empAttitudeSkill.setCreatedAt(createdAt);
        empAttitudeSkill.setCreatedBy(createdBy);
        empAttitudeSkill.setUpdatedAt(updateAt);
        empAttitudeSkill.setUpdatedBy(updateBy);
        return empAttitudeSkill;

    }
}

