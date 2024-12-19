package com.example.LokaKarya.Dto.EmpAttitudeSkill;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Entity.EmpAttitudeSkill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAttitudeSkillReqDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("attitude_skill_id")
    private UUID attitudeSkillId;
    @JsonProperty("attitude_skill_name")
    private String attitudeSkillName;
    @JsonProperty("group_attitude_skill_name")
    private String groupAttitudeSkillName;
    @JsonProperty("group_attitude_skill_percentage")
    private Integer groupAttitudeSkillPercentage;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;

    public static EmpAttitudeSkillReqDto fromEntity(EmpAttitudeSkill empAttitudeSkill) {
            EmpAttitudeSkillReqDto empAttitudeSkillReqDto = new EmpAttitudeSkillReqDto();
            empAttitudeSkillReqDto.setUserId(empAttitudeSkill.getUser().getId());
            empAttitudeSkillReqDto.setAttitudeSkillId(empAttitudeSkill.getAttitudeSkill().getId());
            empAttitudeSkillReqDto.setAttitudeSkillName(empAttitudeSkill.getAttitudeSkill().getAttitudeSkill());
            empAttitudeSkillReqDto.setGroupAttitudeSkillName(empAttitudeSkill.getAttitudeSkill().getGroupAttitudeSkill().getGroupName());
            empAttitudeSkillReqDto.setGroupAttitudeSkillPercentage(empAttitudeSkill.getAttitudeSkill().getGroupAttitudeSkill().getPercentage());
            empAttitudeSkillReqDto.setScore(empAttitudeSkill.getScore());
            empAttitudeSkillReqDto.setAssessmentYear(empAttitudeSkill.getAssessmentYear());
            return empAttitudeSkillReqDto;
        }
}

