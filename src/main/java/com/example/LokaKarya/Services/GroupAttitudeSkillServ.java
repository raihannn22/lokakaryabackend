package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface GroupAttitudeSkillServ {
    List<GroupAttitudeSkillDto> getAllGroupAttitudeSkill();
    GroupAttitudeSkillDto getGroupAttitudeSkillById(UUID id);
    GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillReqDto GroupAttitudeSkillDto);
    GroupAttitudeSkillDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillReqDto GroupAttitudeSkillDto);
    Boolean deleteGroupAttitudeSkill(UUID id);
}

