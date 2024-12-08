package com.example.LokaKarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillWithDetailsDto;
import com.example.LokaKarya.Entity.GroupAttitudeSkill;
import com.example.LokaKarya.Repository.GroupAttitudeSkillRepo;
import com.example.LokaKarya.Services.GroupAttitudeSkillServ;
import com.example.LokaKarya.util.GetUserUtil;

@Service
public class GroupAttitudeSkillServImpl implements GroupAttitudeSkillServ {

    private final Logger Log = LoggerFactory.getLogger(GroupAttitudeSkillServImpl.class);

    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<GroupAttitudeSkillReqDto> getAllGroupAttitudeSkill() {
        Log.info("Start getAllGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        List<GroupAttitudeSkill> response = groupAttitudeSkillRepo.findAll();
        List<GroupAttitudeSkillReqDto> groupAttitudeSkillReqDto = new ArrayList<>();

        for (GroupAttitudeSkill groupAttitudeSkill : response) {
            groupAttitudeSkillReqDto.add(GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkill));
        }
        Log.info("End getAllGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        return groupAttitudeSkillReqDto;
    }

    @Override
    public GroupAttitudeSkillReqDto getGroupAttitudeSkillById(UUID id) {
        Log.info("Start getGroupAttitudeSkillById in GroupAttitudeSkillServImpl");
        GroupAttitudeSkill groupAttitudeSkill = groupAttitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("GroupAttitudeSkill not found"));
        Log.info("End getAttitudeSkillById in AttitudeSkillServImpl");
        return GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkill);
    }

    @Override
    public List<GroupAttitudeSkillWithDetailsDto> getAllGroupAttitudeSkillsWithDetails() {
        List<GroupAttitudeSkill> skills = groupAttitudeSkillRepo.findAll();
        return skills.stream()
            .map(this::convertToGroupAttitudeSkillWithDetailsDto)
            .collect(Collectors.toList());
    }

    private GroupAttitudeSkillWithDetailsDto convertToGroupAttitudeSkillWithDetailsDto(GroupAttitudeSkill groupAttitudeSkill) {
        List<AttitudeSkillReqDto> attitudeSkillDtos = groupAttitudeSkill.getAttitudeSkill().stream()
            .map(attitudeSkill -> AttitudeSkillReqDto.fromEntity(attitudeSkill))
            .collect(Collectors.toList());

        return new GroupAttitudeSkillWithDetailsDto(
            groupAttitudeSkill.getId(),
            groupAttitudeSkill.getGroupName(),
            groupAttitudeSkill.getPercentage(),
            groupAttitudeSkill.getEnabled(),
            attitudeSkillDtos
        );
    }




    @Override
    public GroupAttitudeSkillReqDto createGroupAttitudeSkill(GroupAttitudeSkillDto groupAttitudeSkillDto) {
        // UUID currentUser = getUserUtil.getCurrentUser().getId();
        
            GroupAttitudeSkill groupAttitudeSkill = groupAttitudeSkillDto.toEntity(groupAttitudeSkillDto, null, null, null, new java.util.Date());
            groupAttitudeSkillRepo.save(groupAttitudeSkill);
            return GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkillRepo.save(groupAttitudeSkill));
    }

    @Override
    public GroupAttitudeSkillReqDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillDto groupAttitudeSkillDto) {
        Log.info("Start updateAttitudeSkill in AttitudeSkillServImpl");
        // UUID currentUser = getUserUtil.getCurrentUser().getId();
        GroupAttitudeSkill groupAttitudeSkill = groupAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupAttitudeSkill not found"));
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());     
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        // groupAttitudeSkill.setUpdatedBy(currentUser);
        groupAttitudeSkill.setUpdatedAt(new java.util.Date());
        groupAttitudeSkillRepo.save(groupAttitudeSkill);
        Log.info("End updateAttitudeSkill in AttitudeSkillServImpl");
        return GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkillRepo.save(groupAttitudeSkill));
    }

    @Override
    public Boolean deleteGroupAttitudeSkill(UUID id) {
        Log.info("Start deleteGroupAttitudeSkill in GroupAttitudeSkillServImpl");

        if (groupAttitudeSkillRepo.existsById(id)) {
            groupAttitudeSkillRepo.deleteById(id);  // hanya menghapus AttitudeSkill berdasarkan id
            Log.info("End deleteGroupAttitudeSkill in GroupAttitudeSkillServImpl");
            return true;
        }
        throw new RuntimeException("GroupAttitudeSkill not found");
    }

    
}
