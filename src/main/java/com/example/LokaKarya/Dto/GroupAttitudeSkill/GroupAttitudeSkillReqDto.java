package com.example.lokakarya.Dto.GroupAttitudeSkill;

import com.example.lokakarya.Entity.GroupAttitudeSkill;
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
public class GroupAttitudeSkillReqDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;

    public static GroupAttitudeSkillReqDto fromEntity(GroupAttitudeSkill groupAttitudeSkill) {
            GroupAttitudeSkillReqDto groupAttitudeSkillReqDto = new GroupAttitudeSkillReqDto();
            groupAttitudeSkillReqDto.setId(groupAttitudeSkill.getId());
            groupAttitudeSkillReqDto.setGroupName(groupAttitudeSkill.getGroupName());
            groupAttitudeSkillReqDto.setPercentage(groupAttitudeSkill.getPercentage());
            groupAttitudeSkillReqDto.setEnabled(groupAttitudeSkill.getEnabled());
            return groupAttitudeSkillReqDto;
        }
}


