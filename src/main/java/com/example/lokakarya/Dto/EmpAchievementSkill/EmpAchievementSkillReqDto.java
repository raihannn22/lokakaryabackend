package com.example.lokakarya.Dto.EmpAchievementSkill;

import com.example.lokakarya.Entity.EmpAchievementSkill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAchievementSkillReqDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("achievement_id")
    private UUID achievementId;
    @JsonProperty("achievement_name")
    private String achievement;
    @JsonProperty("group_achievement_name")
    private String groupAchievement;
    @JsonProperty("group_achievement_percentage")
    private Integer groupAchievementPercentage;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;

    public static EmpAchievementSkillReqDto fromEntity(EmpAchievementSkill empAchievementSkill) {
            EmpAchievementSkillReqDto empAchievementSkillReqDto = new EmpAchievementSkillReqDto();
            empAchievementSkillReqDto.setId(empAchievementSkill.getId());
            empAchievementSkillReqDto.setUserId(empAchievementSkill.getUser().getId());
            empAchievementSkillReqDto.setFullName(empAchievementSkill.getUser().getFullName());
            empAchievementSkillReqDto.setNotes(empAchievementSkill.getNotes());
            empAchievementSkillReqDto.setAchievementId(empAchievementSkill.getAchievement().getId());
            empAchievementSkillReqDto.setAchievement(empAchievementSkill.getAchievement().getAchievement());
            empAchievementSkillReqDto.setGroupAchievement(empAchievementSkill.getAchievement().getGroupAchievement().getGroupName());
        empAchievementSkillReqDto.setGroupAchievementPercentage(empAchievementSkill.getAchievement().getGroupAchievement().getPercentage());
            empAchievementSkillReqDto.setScore(empAchievementSkill.getScore());
            empAchievementSkillReqDto.setAssessmentYear(empAchievementSkill.getAssessmentYear());
            return empAchievementSkillReqDto;
        }
}

