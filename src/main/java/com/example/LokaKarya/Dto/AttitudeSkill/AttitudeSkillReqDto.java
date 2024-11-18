package com.example.LokaKarya.Dto.AttitudeSkill;

import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.LokaKarya.Entity.AttitudeSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AttitudeSkillReqDto {
    @JsonProperty("attitude_skill")
    private String attitudeSkill;
    @JsonProperty("group_id")
    private UUID groupId;
    @JsonProperty("enabled")
    private Integer enabled;

    public static AttitudeSkill toEntity(AttitudeSkillReqDto attitudeSkillDto) {
        AttitudeSkill attitudeSkill = new AttitudeSkill();
        attitudeSkill.setAttitudeSkill(attitudeSkillDto.getAttitudeSkill());
        attitudeSkill.setGroupId(attitudeSkillDto.getGroupId());
        attitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        return attitudeSkill;
    }
}

