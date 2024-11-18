package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementReqDto;
import com.example.LokaKarya.Entity.GroupAchievement;
import com.example.LokaKarya.Repository.GroupAchievementRepo;
import com.example.LokaKarya.Services.GroupAchievementServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupAchievementServImpl implements GroupAchievementServ {

    private final Logger Log = LoggerFactory.getLogger(GroupAchievementServImpl.class);

    @Autowired
    private GroupAchievementRepo groupAchievementRepo;

    @Override
    public List<GroupAchievementDto> getAllGroupAchievement() {
       Log.info("Start getAllGroupAchievement in GroupAchievementServImpl");
        List<GroupAchievement> response = groupAchievementRepo.findAll();
        List<GroupAchievementDto> groupAchievementList = new ArrayList<>();

        for (GroupAchievement groupAchievement : response) {
            GroupAchievementDto groupAchievementDto = GroupAchievementDto.fromEntity(groupAchievement);
            groupAchievementList.add(groupAchievementDto);
        }
       Log.info("End getAllGroupAchievement in GroupAchievementServImpl");
        return groupAchievementList;
    }

    @Override
    public GroupAchievementDto getGroupAchievementById(UUID id) {
       Log.info("Start getGroupAchievementById in GroupAchievementServImpl");
        Optional<GroupAchievement> groupAchivement = groupAchievementRepo.findById(id);
       Log.info("End getGroupAchievementById in GroupAchievementServImpl");
        return groupAchivement.map(GroupAchievementDto::fromEntity).orElse(null);
    }

    @Override
    public GroupAchievementDto createGroupAchievement(GroupAchievementReqDto groupAchievementDto) {
       Log.info("Start createGroupAchievement in GroupAchievementServImpl");

        GroupAchievement groupAchievement = GroupAchievementReqDto.toEntity(groupAchievementDto);

        groupAchievementRepo.save(groupAchievement);
       Log.info("End createGroupAchievement in GroupAchievementServImpl");
        return GroupAchievementDto.fromEntity(groupAchievement);
    }

    @Override
    public GroupAchievementDto updateGroupAchievement (UUID id, GroupAchievementReqDto groupAchievementDto) {
       Log.info("Start updateGroupAchievement in GroupAchievementServImpl");
        GroupAchievement findGroupAchievement  = groupAchievementRepo.findById(id).orElseThrow(() -> new RuntimeException("Group Achievement not found"));

        updateGroupAchievementFields(findGroupAchievement , groupAchievementDto);

        groupAchievementRepo.save(findGroupAchievement);
       Log.info("End updateGroupAchievement in GroupAchievementServImpl");
        return GroupAchievementDto.fromEntity(findGroupAchievement);
    }

    @Override
    public Boolean deleteGroupAchievement(UUID id) {
       Log.info("Start deleteGroupAchievement in GroupAchievementServImpl");
        GroupAchievement findGroupAchievement = groupAchievementRepo.findById(id).orElseThrow(() -> new RuntimeException("Group Achievement not found"));
        groupAchievementRepo.delete(findGroupAchievement);
       Log.info("End deleteGroupAchievement in GroupAchievementServImpl");
        return true;
    }

    private void updateGroupAchievementFields(GroupAchievement existingGroupAchievement, GroupAchievementReqDto groupAchievementDto) {
        if (groupAchievementDto.getGroupName() != null) {
            existingGroupAchievement.setGroupName(groupAchievementDto.getGroupName());
        }
        if (groupAchievementDto.getPercentage() != null) {
            existingGroupAchievement.setPercentage(groupAchievementDto.getPercentage());
        }
        if (groupAchievementDto.getEnabled() != null) {
            existingGroupAchievement.setEnabled(groupAchievementDto.getEnabled());
        }
        

    }
}
