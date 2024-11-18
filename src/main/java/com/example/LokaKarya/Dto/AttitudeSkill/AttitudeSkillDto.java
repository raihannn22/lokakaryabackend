package com.example.LokaKarya.Dto.AttitudeSkill;

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
public class AttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("attitude_skill")
    private String attitudeSkill;
    @JsonProperty("group_id")
    private UUID groupId;
    @JsonProperty("enabled")
    private Integer enabled;


    public static AttitudeSkillDto fromEntity(AttitudeSkill attitudeSkill) {
        AttitudeSkillDto attitudeSkillDto = new AttitudeSkillDto();
        attitudeSkillDto.setId(attitudeSkill.getId());
        attitudeSkillDto.setAttitudeSkill(attitudeSkill.getAttitudeSkill());
        attitudeSkillDto.setGroupId(attitudeSkill.getGroupId());
        attitudeSkillDto.setEnabled(attitudeSkill.getEnabled());
        return attitudeSkillDto;
    }

    public static AttitudeSkill toEntity(AttitudeSkillDto attitudeSkillDto) {
        AttitudeSkill attitudeSkill = new AttitudeSkill();
        attitudeSkill.setId(attitudeSkillDto.getId());
        attitudeSkill.setAttitudeSkill(attitudeSkillDto.getAttitudeSkill());
        attitudeSkill.setGroupId(attitudeSkillDto.getGroupId());
        attitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        return attitudeSkill;
    }
}

