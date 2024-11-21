package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;

import java.util.List;
import java.util.UUID;

public interface GroupAttitudeSkillServ {
    List<GroupAttitudeSkillReqDto> getAllGroupAttitudeSkill();
    GroupAttitudeSkillReqDto getGroupAttitudeSkillById(UUID id);
    GroupAttitudeSkillReqDto createGroupAttitudeSkill(GroupAttitudeSkillDto GroupAttitudeSkillDto);
    GroupAttitudeSkillReqDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillDto GroupAttitudeSkillDto);
    Boolean deleteGroupAttitudeSkill(UUID id);
}

