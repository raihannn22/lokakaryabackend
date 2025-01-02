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

import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementReqDto;
import com.example.lokakarya.Entity.GroupAchievement;
import com.example.lokakarya.Repository.GroupAchievementRepo;
import com.example.lokakarya.Services.GroupAchievementServ;
import com.example.lokakarya.util.GetUserUtil;

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

    // @Override
    // public List<GroupAchievementReqDto> getPaginatedGroupAchievement(int page, int size, String sort, String direction, String searchKeyword) {
    //     Log.info("Start getPaginatedGroupAchievement in GroupAchievementServImpl");
        
    //     Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
    //     Pageable pageable = PageRequest.of(page, size, sorting);
    //     Page<GroupAchievement> groupAchievementPage;

    //     if (searchKeyword != null && !searchKeyword.isEmpty()) {
    //         groupAchievementPage = groupAchievementRepo.findByGroupNameContainingIgnoreCase(searchKeyword, pageable);
    //     } else {
    //         groupAchievementPage = groupAchievementRepo.findAll(pageable);
    //     }

    //     List<GroupAchievementReqDto> groupAchievementReqDto = new ArrayList<>();
    //     for (GroupAchievement groupAchievement : groupAchievementPage.getContent()) {
    //         groupAchievementReqDto.add(GroupAchievementReqDto.fromEntity(groupAchievement));
    //     }
        
    //     Log.info("End getPaginatedGroupAchievement in GroupAchievementServImpl");
    //     return groupAchievementReqDto;
    // }

    @Override
    public List<GroupAchievementReqDto> getPaginatedGroupAchievement(int page, int size, String sort, String direction, String searchKeyword) {
        Log.info("Start getPaginatedGroupAchievement in GroupAchievementServImpl");
        
        Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<GroupAchievement> groupAchievementPage;

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            try {
                // Coba untuk mengonversi searchKeyword menjadi Double
                Double percentage = Double.valueOf(searchKeyword);
                // Jika berhasil, cari berdasarkan percentage
                groupAchievementPage = groupAchievementRepo.findByPercentage(percentage, pageable);
            } catch (NumberFormatException e) {
                // Jika tidak bisa dikonversi, cari berdasarkan groupName
                groupAchievementPage = groupAchievementRepo.findByGroupNameContainingIgnoreCase(searchKeyword, pageable);
            }
        } else {
            groupAchievementPage = groupAchievementRepo.findAll(pageable);
        }

        List<GroupAchievementReqDto> groupAchievementReqDto = new ArrayList<>();
        for (GroupAchievement groupAchievement : groupAchievementPage.getContent()) {
            groupAchievementReqDto.add(GroupAchievementReqDto.fromEntity(groupAchievement));
        }
        
        Log.info("End getPaginatedGroupAchievement in GroupAchievementServImpl");
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

    @Override
    public List<GroupAchievementReqDto> getAllGroupAchievementEnabled() {
        Log.info("Start getAllGroupAchievementEnabled in GroupAchievementServImpl");
        List<GroupAchievement> response = groupAchievementRepo.findByEnabled(1);
        List<GroupAchievementReqDto> groupAchievementReqDto = new ArrayList<>();

        for (GroupAchievement groupAchievement : response) {
            groupAchievementReqDto.add(GroupAchievementReqDto.fromEntity(groupAchievement));
        }
        Log.info("End getAllGroupAchievementEnabled in GroupAchievementServImpl");
        return groupAchievementReqDto;
    }

    @Override
    public long count() {
        return groupAchievementRepo.count(); // Get total count of records
    }

    @Override
    public long countBySearchKeyword(String searchKeyword) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            return groupAchievementRepo.countByGroupNameContainingIgnoreCase(searchKeyword);
        }
        return groupAchievementRepo.count(); // Mengembalikan total count jika tidak ada keyword pencarian
    }


}
