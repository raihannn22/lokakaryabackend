package com.example.LokaKarya.Dto.EmpAchievementSkill;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
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
public class EmpAchievementSkillReqDto {
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

    public static EmpAchievementSkill toEntity(EmpAchievementSkillReqDto empAchievementSkillDto) {
        EmpAchievementSkill empAchievementSkill = new EmpAchievementSkill();
        empAchievementSkill.setUserId(empAchievementSkillDto.getUserId());
        empAchievementSkill.setNotes(empAchievementSkillDto.getNotes());
        empAchievementSkill.setAchievementId(empAchievementSkillDto.getAchievementId());
        empAchievementSkill.setScore(empAchievementSkillDto.getScore());
        empAchievementSkill.setAssessmentYear(empAchievementSkillDto.getAssessmentYear());
        return empAchievementSkill;
    }
}

