package com.example.lokakarya.Dto.GroupAttitudeSkill;
import com.example.lokakarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttitudeSkillWithDetailsDto {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("percentage")
    private Integer percentage;

    @JsonProperty("enabled")
    private Integer enabled;

    @JsonProperty("attitude_skills")
    private List<AttitudeSkillReqDto> attitudeSkills;

}