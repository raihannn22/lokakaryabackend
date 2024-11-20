package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementReqDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;
import com.example.LokaKarya.Entity.GroupAchievement;
import com.example.LokaKarya.Entity.GroupAttitudeSkill;
import com.example.LokaKarya.Repository.GroupAttitudeSkillRepo;
import com.example.LokaKarya.Services.GroupAttitudeSkillServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupAttitudeSkillServImpl implements GroupAttitudeSkillServ {

    private final Logger Log = LoggerFactory.getLogger(GroupAttitudeSkillServImpl.class);

    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Override
    public List<GroupAttitudeSkillDto> getAllGroupAttitudeSkill() {
        Log.info("Start getAllGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        List<GroupAttitudeSkill> response = groupAttitudeSkillRepo.findAll();
        List<GroupAttitudeSkillDto> groupAttitudeSkillList = new ArrayList<>();

        for (GroupAttitudeSkill groupAttitudeSkill : response) {
            GroupAttitudeSkillDto groupAttitudeSkillDto = GroupAttitudeSkillDto.fromEntity(groupAttitudeSkill);
            groupAttitudeSkillList.add(groupAttitudeSkillDto);
        }
        Log.info("End getAllGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        return groupAttitudeSkillList;
    }

    @Override
    public GroupAttitudeSkillDto getGroupAttitudeSkillById(UUID id) {
        Log.info("Start getGroupAttitudeSkillById in GroupAttitudeSkillServImpl");
        Optional<GroupAttitudeSkill> groupAttitudeSkill = groupAttitudeSkillRepo.findById(id);
        Log.info("End getGroupAttitudeSkillById in GroupAttitudeSkillServImpl");
        return groupAttitudeSkill.map(GroupAttitudeSkillDto::fromEntity).orElse(null);
    }

    @Override
    public GroupAttitudeSkillDto createGroupAttitudeSkill(GroupAttitudeSkillReqDto groupAttitudeSkillDto) {
        Log.info("Start createGroupAttitudeSkill in GroupAttitudeSkillServImpl");

        GroupAttitudeSkill groupAttitudeSkill = GroupAttitudeSkillReqDto.toEntity(groupAttitudeSkillDto);

        groupAttitudeSkillRepo.save(groupAttitudeSkill);
        Log.info("End createGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        return GroupAttitudeSkillDto.fromEntity(groupAttitudeSkill);
    }

    @Override
    public GroupAttitudeSkillDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillReqDto groupAttitudeSkillDto) {
        Log.info("Start updateGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        GroupAttitudeSkill findGroupAttitudeSkill = groupAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group Attitude Skill not found"));

        updateGroupAttitudeSkillFields(findGroupAttitudeSkill, groupAttitudeSkillDto);

        groupAttitudeSkillRepo.save(findGroupAttitudeSkill);
        Log.info("End updateGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        return GroupAttitudeSkillDto.fromEntity(findGroupAttitudeSkill);
    }

    @Override
    public Boolean deleteGroupAttitudeSkill(UUID id) {
        Log.info("Start deleteGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        GroupAttitudeSkill findGroupAttitudeSkill = groupAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group Attitude Skill not found"));
        groupAttitudeSkillRepo.delete(findGroupAttitudeSkill);
        Log.info("End deleteGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        return true;
    }

    // KONTOLODON
    private void updateGroupAttitudeSkillFields(GroupAttitudeSkill existingGroupAttitudeSkill, GroupAttitudeSkillReqDto groupAttitudeSkillDto) {
        if (groupAttitudeSkillDto.getGroupName() != null) {
            existingGroupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        }
        if (groupAttitudeSkillDto.getPercentage() != null) {
            existingGroupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        }
        if (groupAttitudeSkillDto.getEnabled() != null) {
            existingGroupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        }
        

    }
}
