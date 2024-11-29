package com.example.LokaKarya.Dto.AttitudeSkill;

import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.LokaKarya.Entity.AttitudeSkill;
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
public class AttitudeSkillReqDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("attitude_skill")
    private String attitudeSkill;
    @JsonProperty("group_id")
    private UUID groupId;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("enabled")
    private Integer enabled;

    public static AttitudeSkillReqDto fromEntity(AttitudeSkill attitudeSkill) {
        AttitudeSkillReqDto attitudeSkillReqDto = new AttitudeSkillReqDto();
        attitudeSkillReqDto.setId(attitudeSkill.getId());
        attitudeSkillReqDto.setGroupName(attitudeSkill.getGroupAttitudeSkill().getGroupName());
        attitudeSkillReqDto.setAttitudeSkill(attitudeSkill.getAttitudeSkill());
        attitudeSkillReqDto.setGroupId(attitudeSkill.getGroupAttitudeSkill().getId());
        attitudeSkillReqDto.setEnabled(attitudeSkill.getEnabled());
        return attitudeSkillReqDto;
    }
}

