package com.example.LokaKarya.Dto.GroupAttitudeSkill;

import com.example.LokaKarya.Entity.GroupAttitudeSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttitudeSkillReqDto {
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;

    public static GroupAttitudeSkill toEntity(GroupAttitudeSkillReqDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        return groupAttitudeSkill;
    }
}


