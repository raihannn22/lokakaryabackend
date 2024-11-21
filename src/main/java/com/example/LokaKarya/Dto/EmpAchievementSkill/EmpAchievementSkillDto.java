package com.example.LokaKarya.Dto.EmpAchievementSkill;

import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
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

    
    public static EmpAchievementSkill toEntity(EmpAchievementSkillDto empAchievementSkillDto, Achievement achievement, User user, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        EmpAchievementSkill empAchievementSkill = new EmpAchievementSkill();
        empAchievementSkill.setId(empAchievementSkillDto.getId());
        empAchievementSkill.setUser(user);
        empAchievementSkill.setNotes(empAchievementSkillDto.getNotes());
        empAchievementSkill.setAchievement(achievement);
        empAchievementSkill.setScore(empAchievementSkillDto.getScore());
        empAchievementSkill.setAssessmentYear(empAchievementSkillDto.getAssessmentYear());
        empAchievementSkill.setCreatedAt(createdAt);
        empAchievementSkill.setCreatedBy(createdBy);
        empAchievementSkill.setUpdatedAt(updateAt);
        empAchievementSkill.setUpdatedBy(updateBy);
        return empAchievementSkill;



    }
}

