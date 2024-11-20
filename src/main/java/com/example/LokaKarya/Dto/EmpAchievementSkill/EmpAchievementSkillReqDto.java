package com.example.LokaKarya.Dto.EmpAchievementSkill;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    public static EmpAchievementSkillReqDto fromEntity(EmpAchievementSkill empAchievementSkill) {
            EmpAchievementSkillReqDto empAchievementSkillReqDto = new EmpAchievementSkillReqDto();
            empAchievementSkillReqDto.setUserId(empAchievementSkill.getUser().getId());
            empAchievementSkillReqDto.setNotes(empAchievementSkill.getNotes());
            empAchievementSkillReqDto.setAchievementId(empAchievementSkill.getAchievement().getId());
            empAchievementSkillReqDto.setScore(empAchievementSkill.getScore());
            empAchievementSkillReqDto.setAssessmentYear(empAchievementSkill.getAssessmentYear());
            return empAchievementSkillReqDto;
        }
}

