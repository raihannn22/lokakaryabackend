package com.example.lokakarya.Dto.AttitudeSkill;

import com.example.lokakarya.Entity.AttitudeSkill;
import com.example.lokakarya.Entity.GroupAttitudeSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
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


    
    public static AttitudeSkill toEntity(AttitudeSkillDto attitudeSkillDto, GroupAttitudeSkill groupAttitudeSkill, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        AttitudeSkill attitudeSkill = new AttitudeSkill();
        attitudeSkill.setId(attitudeSkillDto.getId());
        attitudeSkill.setAttitudeSkill(attitudeSkillDto.getAttitudeSkill());
        attitudeSkill.setGroupAttitudeSkill(groupAttitudeSkill);
        attitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        attitudeSkill.setCreatedAt(createdAt);
        attitudeSkill.setCreatedBy(createdBy);
        attitudeSkill.setUpdatedAt(updateAt);
        attitudeSkill.setUpdatedBy(updateBy);
        
        return attitudeSkill;
    }
}

