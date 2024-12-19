package com.example.LokaKarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementReqDto;
import com.example.LokaKarya.Entity.GroupAchievement;
import com.example.LokaKarya.Repository.GroupAchievementRepo;
import com.example.LokaKarya.Services.GroupAchievementServ;
import com.example.LokaKarya.util.GetUserUtil;

@Service
public class GroupAchievementServImpl implements GroupAchievementServ {

    private final Logger Log = LoggerFactory.getLogger(GroupAchievementServImpl.class);

    @Autowired
    private GroupAchievementRepo groupAchievementRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<GroupAchievementReqDto> getAllGroupAchievement() {
        Log.info("Start getAllGroupAchievement in GroupAchievementServImpl");
        List<GroupAchievement> response = groupAchievementRepo.findAll();
        List<GroupAchievementReqDto> groupAchievementReqDto = new ArrayList<>();

        for (GroupAchievement groupAchievement : response) {
            groupAchievementReqDto.add(GroupAchievementReqDto.fromEntity(groupAchievement));
        }
        Log.info("End getAllGroupAchievement in GroupAchievementServImpl");
        return groupAchievementReqDto;
    }

    @Override
    public GroupAchievementReqDto getGroupAchievementById(UUID id) {
        Log.info("Start getGroupAchievementById in GroupAchievementServImpl");
        GroupAchievement groupAchievement = groupAchievementRepo.findById(id).orElseThrow(() -> new RuntimeException("GroupAchievement not found"));
        Log.info("End getAchievementById in AchievementServImpl");
        return GroupAchievementReqDto.fromEntity(groupAchievement);
    }

    @Override
    public GroupAchievementReqDto createGroupAchievement(GroupAchievementDto groupAchievementDto) {
        Log.info("Start createGroupAchievement in GroupAchievementServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        
            GroupAchievement groupAchievement = groupAchievementDto.toEntity(groupAchievementDto, null, null, currentUser, new java.util.Date());
            groupAchievementRepo.save(groupAchievement);
            Log.info("End createGroupAchievement in GroupAchievementServImpl");
            return GroupAchievementReqDto.fromEntity(groupAchievementRepo.save(groupAchievement));
    }

    @Override
    public GroupAchievementReqDto updateGroupAchievement(UUID id, GroupAchievementDto groupAchievementDto) {
        Log.info("Start updateAchievement in AchievementServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        GroupAchievement groupAchievement = groupAchievementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupAchievement not found"));
        groupAchievement.setGroupName(groupAchievementDto.getGroupName());     
        groupAchievement.setPercentage(groupAchievementDto.getPercentage());
        groupAchievement.setEnabled(groupAchievementDto.getEnabled());
        groupAchievement.setUpdatedBy(currentUser);
        groupAchievement.setUpdatedAt(new java.util.Date());
        groupAchievementRepo.save(groupAchievement);
        Log.info("End updateAchievement in AchievementServImpl");
        return GroupAchievementReqDto.fromEntity(groupAchievementRepo.save(groupAchievement));
    }

    @Override
    public Boolean deleteGroupAchievement(UUID id) {
        Log.info("Start deleteGroupAchievement in GroupAchievementServImpl");

        if (groupAchievementRepo.existsById(id)) {
            groupAchievementRepo.deleteById(id); 
            Log.info("End deleteGroupAchievement in GroupAchievementServImpl");
            return true;
        }
        throw new RuntimeException("GroupAchievement not found");
    }

    
}
