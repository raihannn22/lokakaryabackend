package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillWithDetailsDto;

public interface GroupAttitudeSkillServ {
    List<GroupAttitudeSkillReqDto> getAllGroupAttitudeSkill();
    List<GroupAttitudeSkillWithDetailsDto> getAllGroupAttitudeSkillsWithDetails();
    GroupAttitudeSkillReqDto getGroupAttitudeSkillById(UUID id);
    GroupAttitudeSkillReqDto createGroupAttitudeSkill(GroupAttitudeSkillDto GroupAttitudeSkillDto);
    GroupAttitudeSkillReqDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillDto GroupAttitudeSkillDto);
    Boolean deleteGroupAttitudeSkill(UUID id);
}

