package com.example.LokaKarya.Service.Impl;

import com.example.LokaKarya.Dto.GroupAchievementDto;
import com.example.LokaKarya.Entity.tbl_group_achievement;
import com.example.LokaKarya.Repository.GroupAchievementRepo;
import com.example.LokaKarya.Service.GroupAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupAchievementServiceImpl implements GroupAchievementService {

    @Autowired
    private GroupAchievementRepo groupAchievementRepository;

    @Override
    public List<GroupAchievementDto> getAllGroupAchievements() {
        List<tbl_group_achievement> achievements = groupAchievementRepository.findAll();
        return achievements.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public GroupAchievementDto getGroupAchievementById(UUID id) {
        Optional<tbl_group_achievement> achievement = groupAchievementRepository.findById(id);
        return achievement.map(this::convertToDto).orElse(null);
    }

    @Override
    public GroupAchievementDto createGroupAchievement(GroupAchievementDto groupAchievementDto) {
        tbl_group_achievement achievement = convertToEntity(groupAchievementDto);
        tbl_group_achievement savedAchievement = groupAchievementRepository.save(achievement);
        return convertToDto(savedAchievement);
    }

    @Override
    public GroupAchievementDto updateGroupAchievement(UUID id, GroupAchievementDto groupAchievementDto) {
        Optional<tbl_group_achievement> existingAchievement = groupAchievementRepository.findById(id);
        if (existingAchievement.isPresent()) {
            tbl_group_achievement achievement = existingAchievement.get();
            achievement.setGroupName(groupAchievementDto.getGroupName());
            achievement.setPercentage(groupAchievementDto.getPercentage());
            achievement.setEnabled(groupAchievementDto.getEnabled());
            achievement.setUpdatedAt(groupAchievementDto.getUpdatedAt());
            achievement.setUpdatedBy(groupAchievementDto.getUpdatedBy());
            tbl_group_achievement updatedAchievement = groupAchievementRepository.save(achievement);
            return convertToDto(updatedAchievement);
        }
        return null;
    }

    @Override
    public void deleteGroupAchievement(UUID id) {
        groupAchievementRepository.deleteById(id);
    }

    private GroupAchievementDto convertToDto(tbl_group_achievement entity) {
        return new GroupAchievementDto(
            entity.getId(),
            entity.getGroupName(),
            entity.getPercentage(),
            entity.getEnabled(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getUpdatedAt(),
            entity.getUpdatedBy()
        );
    }

    private tbl_group_achievement convertToEntity(GroupAchievementDto dto) {
        tbl_group_achievement entity = new tbl_group_achievement();
        entity.setGroupName(dto.getGroupName());
        entity.setPercentage(dto.getPercentage());
        entity.setEnabled(dto.getEnabled());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }
}
