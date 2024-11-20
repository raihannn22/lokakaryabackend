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
public class GroupAttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;


    public static GroupAttitudeSkillDto fromEntity(GroupAttitudeSkill groupAttitudeSkill) {
        GroupAttitudeSkillDto groupAttitudeSkillDto = new GroupAttitudeSkillDto();
        groupAttitudeSkillDto.setId(groupAttitudeSkill.getId());
        groupAttitudeSkillDto.setGroupName(groupAttitudeSkill.getGroupName());
        groupAttitudeSkillDto.setPercentage(groupAttitudeSkill.getPercentage());
        groupAttitudeSkillDto.setEnabled(groupAttitudeSkill.getEnabled());
        return groupAttitudeSkillDto;
    }

    public static GroupAttitudeSkill toEntity(GroupAttitudeSkillDto groupAttitudeSkillDto) {
        GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
        groupAttitudeSkill.setId(groupAttitudeSkillDto.getId());
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        return groupAttitudeSkill;
    }
}

