package com.example.LokaKarya.Dto.EmpTechnicalSkill;

import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.example.LokaKarya.Entity.TechnicalSkill;
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



    public static EmpTechnicalSkill toEntity(EmpTechnicalSkillDto empTechnicalSkillDto, TechnicalSkill technicalSkill, User user, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        EmpTechnicalSkill empTechnicalSkill = new EmpTechnicalSkill();
        empTechnicalSkill.setId(empTechnicalSkillDto.getId());
        empTechnicalSkill.setUser(user);
        empTechnicalSkill.setTechnicalSkill(technicalSkill);
        empTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
        empTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
        empTechnicalSkill.setCreatedAt(createdAt);
        empTechnicalSkill.setCreatedBy(createdBy);
        empTechnicalSkill.setUpdatedAt(updateAt);
        empTechnicalSkill.setUpdatedBy(updateBy);
        return empTechnicalSkill;
    }


    // public static EmpTechnicalSkillDto fromEntity(EmpTechnicalSkill empTechnicalSkill) {
    //     EmpTechnicalSkillDto empTechnicalSkillDto = new EmpTechnicalSkillDto();
    //     empTechnicalSkillDto.setId(empTechnicalSkill.getId());
    //     empTechnicalSkillDto.setUserId(empTechnicalSkill.getUserId());
    //     empTechnicalSkillDto.setTechnicalSkillId(empTechnicalSkill.getTechnicalSkillId());
    //     empTechnicalSkillDto.setScore(empTechnicalSkill.getScore());
    //     empTechnicalSkillDto.setAssessmentYear(empTechnicalSkill.getAssessmentYear());
    //     return empTechnicalSkillDto;
    // }
}

