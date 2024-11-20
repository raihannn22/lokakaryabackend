package com.example.LokaKarya.Dto.EmpAchievementSkill;

import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAchievementSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("achievement_id")
    private UUID achievementId;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;

    
    public static EmpAchievementSkill toEntity(EmpAchievementSkillDto empAchievementSkillDto, Achievement achievement, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        EmpAchievementSkill empAchievementSkill = new EmpAchievementSkill();
        empAchievementSkill.setId(empAchievementSkillDto.getId());
        empAchievementSkill.setUserId(empAchievementSkillDto.getUserId());
        empAchievementSkill.setNotes(empAchievementSkillDto.getNotes());
        empAchievementSkill.setAchievement(achievement);
        empAchievementSkill.setScore(empAchievementSkillDto.getScore());
        empAchievementSkill.setAssessmentYear(empAchievementSkillDto.getAssessmentYear());
        return empAchievementSkill;


        // public static EmpAchievementSkillDto fromEntity(EmpAchievementSkill empAchievementSkill) {
        //     EmpAchievementSkillDto empAchievementSkillDto = new EmpAchievementSkillDto();
        //     empAchievementSkillDto.setId(empAchievementSkill.getId());
        //     empAchievementSkillDto.setUserId(empAchievementSkill.getUserId());
        //     empAchievementSkillDto.setNotes(empAchievementSkill.getNotes());
        //     empAchievementSkillDto.setAchievementId(empAchievementSkill.getAchievementId());
        //     empAchievementSkillDto.setScore(empAchievementSkill.getScore());
        //     empAchievementSkillDto.setAssessmentYear(empAchievementSkill.getAssessmentYear());
        //     return empAchievementSkillDto;
        // }
    }
}

