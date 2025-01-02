package com.example.lokakarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementReqDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillWithDetailsDto;
import com.example.lokakarya.Entity.GroupAchievement;
import com.example.lokakarya.Entity.GroupAttitudeSkill;
import com.example.lokakarya.Repository.GroupAttitudeSkillRepo;
import com.example.lokakarya.Services.GroupAttitudeSkillServ;
import com.example.lokakarya.util.GetUserUtil;

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
    public List<GroupAttitudeSkillReqDto> getPaginatedGroupAttitudeSkill(int page, int size, String sort, String direction, String searchKeyword) {
        Log.info("Start getPaginatedGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        
        Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<GroupAttitudeSkill> groupAttitudeSkillPage;

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            groupAttitudeSkillPage = groupAttitudeSkillRepo.findByGroupNameContainingIgnoreCase(searchKeyword, pageable);
        } else {
            groupAttitudeSkillPage = groupAttitudeSkillRepo.findAll(pageable);
        }

        List<GroupAttitudeSkillReqDto> groupAttitudeSkillReqDto = new ArrayList<>();
        for (GroupAttitudeSkill groupAttitudeSkill : groupAttitudeSkillPage.getContent()) {
            groupAttitudeSkillReqDto.add(GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkill));
        }
        
        Log.info("End getPaginatedGroupAttitudeSkill in GroupAttitudeSkillServImpl");
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
        Log.info("Start getAllGroupAttitudeSkillsWithDetails in GroupAttitudeSkillServImpl");
        List<GroupAttitudeSkill> skills = groupAttitudeSkillRepo.findByEnabled(1);
        Log.info("End getAllGroupAttitudeSkillsWithDetails in GroupAttitudeSkillServImpl");
        return skills.stream()
            .map(this::convertToGroupAttitudeSkillWithDetailsDto)
            .collect(Collectors.toList());
    }

    private GroupAttitudeSkillWithDetailsDto convertToGroupAttitudeSkillWithDetailsDto(GroupAttitudeSkill groupAttitudeSkill) {
        Log.info("Start convertToGroupAttitudeSkillWithDetailsDto in GroupAttitudeSkillServImpl");
        List<AttitudeSkillReqDto> attitudeSkillDtos = groupAttitudeSkill.getAttitudeSkill().stream()
            .map(attitudeSkill -> AttitudeSkillReqDto.fromEntity(attitudeSkill))
            .collect(Collectors.toList());
        Log.info("End convertToGroupAttitudeSkillWithDetailsDto in GroupAttitudeSkillServImpl");
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
        Log.info("Start createGroupAttitudeSkill in GroupAttitudeSkillServImpl");
            GroupAttitudeSkill groupAttitudeSkill = groupAttitudeSkillDto.toEntity(groupAttitudeSkillDto, null, null, null, new java.util.Date());
            groupAttitudeSkillRepo.save(groupAttitudeSkill);
            Log.info("End createGroupAttitudeSkill in GroupAttitudeSkillServImpl");
            return GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkillRepo.save(groupAttitudeSkill));
    }

    @Override
    public GroupAttitudeSkillReqDto updateGroupAttitudeSkill(UUID id, GroupAttitudeSkillDto groupAttitudeSkillDto) {
        Log.info("Start updateAttitudeSkill in AttitudeSkillServImpl");        
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        GroupAttitudeSkill groupAttitudeSkill = groupAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupAttitudeSkill not found"));
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());     
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        groupAttitudeSkill.setUpdatedBy(currentUser);
        groupAttitudeSkill.setUpdatedAt(new java.util.Date());
        groupAttitudeSkillRepo.save(groupAttitudeSkill);
        Log.info("End updateAttitudeSkill in AttitudeSkillServImpl");
        return GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkillRepo.save(groupAttitudeSkill));
    }

    @Override
    public Boolean deleteGroupAttitudeSkill(UUID id) {
        Log.info("Start deleteGroupAttitudeSkill in GroupAttitudeSkillServImpl");
        if (groupAttitudeSkillRepo.existsById(id)) {
            groupAttitudeSkillRepo.deleteById(id);
            Log.info("End deleteGroupAttitudeSkill in GroupAttitudeSkillServImpl");
            return true;
        }
        throw new RuntimeException("GroupAttitudeSkill not found");
    }

    @Override
    public List<GroupAttitudeSkillReqDto> getAllGroupAttitudeSkillEnabled() {
        Log.info("Start getAllGroupAttitudeSkillEnabled in GroupAttitudeSkillServImpl");
        List<GroupAttitudeSkill> response = groupAttitudeSkillRepo.findByEnabled(1);
        List<GroupAttitudeSkillReqDto> groupAttitudeSkillReqDto = new ArrayList<>();
        for (GroupAttitudeSkill groupAttitudeSkill : response) {
            groupAttitudeSkillReqDto.add(GroupAttitudeSkillReqDto.fromEntity(groupAttitudeSkill));
        }
        Log.info("End getAllGroupAttitudeSkillEnabled in GroupAttitudeSkillServImpl");
        return groupAttitudeSkillReqDto;

    }
    @Override
    public long count() {
        return groupAttitudeSkillRepo.count(); // Get total count of records
    }

    @Override
    public long countBySearchKeyword(String searchKeyword) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            return groupAttitudeSkillRepo.countByGroupNameContainingIgnoreCase(searchKeyword);
        }
        return groupAttitudeSkillRepo.count(); // Mengembalikan total count jika tidak ada keyword pencarian
    }


}
