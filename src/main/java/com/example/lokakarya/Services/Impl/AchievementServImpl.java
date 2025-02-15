package com.example.lokakarya.Services.Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.lokakarya.Dto.Achievement.AchievementDto;
import com.example.lokakarya.Dto.Achievement.AchievementReqDto;
import com.example.lokakarya.Entity.Achievement;
import com.example.lokakarya.Entity.GroupAchievement;
import com.example.lokakarya.Repository.AchievementRepo;
import com.example.lokakarya.Repository.GroupAchievementRepo;
import com.example.lokakarya.Services.AchievementServ;
import com.example.lokakarya.util.GetUserUtil;

@Service
public class AchievementServImpl implements AchievementServ {
    private final Logger Log = LoggerFactory.getLogger(AchievementServImpl.class);
    @Autowired
    AchievementRepo achievementRepo;

    @Autowired
    GroupAchievementRepo groupAchievementRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<AchievementReqDto> getAllAchievement() {
        List<Achievement> response = achievementRepo.findAll();
        List<AchievementReqDto> achievementReqDto = new ArrayList<>();
        for (Achievement achievement : response) {
            achievementReqDto.add(AchievementReqDto.fromEntity(achievement));
        }
        return achievementReqDto;
    }

    @Override
    public AchievementReqDto getAchievementById(UUID id) {
        Log.info("Start getAchievementById in AchievementServImpl");
        Achievement achievement = achievementRepo.findById(id).orElseThrow(() -> new RuntimeException("Achievement not found"));
        Log.info("End getAchievementById in AchievementServImpl");
        return AchievementReqDto.fromEntity(achievement);
    }

    @Override
    public List<AchievementReqDto> getPaginatedAchievement(int page, int size, String sort, String direction, String searchKeyword) {
        Log.info("Start getPaginatedAchievement in AchievementServImpl");
        Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<Achievement> achievementPage;
        if (searchKeyword != null && !searchKeyword.isEmpty()) {            
            achievementPage = achievementRepo.findByAchievementContainingIgnoreCase(searchKeyword, pageable);
        } else {
            achievementPage = achievementRepo.findAll(pageable);
        }
        List<AchievementReqDto> achievementReqDto = new ArrayList<>();
        for (Achievement achievement : achievementPage.getContent()) {
            achievementReqDto.add(AchievementReqDto.fromEntity(achievement));
        }
        Log.info("End getPaginatedAchievement in AchievementServImpl");
        return achievementReqDto;
    }

    @Override
    public List<AchievementReqDto> getAchievementsByGroupId(UUID groupId) {
        List<Achievement> achievements = achievementRepo.findByGroupAchievementId(groupId);
        List<AchievementReqDto> achievementReqDtos = new ArrayList<>();
        for (Achievement achievement : achievements) {
            achievementReqDtos.add(AchievementReqDto.fromEntity(achievement));
        }
        return achievementReqDtos;
    }

    @Override
    public AchievementReqDto createAchievement(AchievementDto achievementDto) {
        Log.info("Start createAchievement in AchievementServImpl");
        Optional<GroupAchievement> groupAchievement = groupAchievementRepo.findById(achievementDto.getGroupId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        if (groupAchievement.isPresent()) {
            Achievement achievement = achievementDto.toEntity(achievementDto, groupAchievement.get(), null, null, currentUser, new java.util.Date());
            achievementRepo.save(achievement);
            Log.info("End createAchievement in AchievementServImpl");
            return AchievementReqDto.fromEntity(achievementRepo.save(achievement));
        }else {
            throw new RuntimeException("Achievement not found");
        }
    }

    @Override
    public AchievementReqDto updateAchievement(UUID id, AchievementDto achievementDto) {
        Log.info("Start updateAchievement in AchievementServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        Achievement achievement = achievementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));
        achievement.setAchievement(achievementDto.getAchievement());     
        achievement.setGroupAchievement(groupAchievementRepo.findById(achievementDto.getGroupId()).orElseThrow(() -> new RuntimeException("Group Achievement not found")));
        achievement.setEnabled(achievementDto.getEnabled());
        achievement.setUpdatedBy(currentUser);
        achievement.setUpdatedAt(new java.util.Date());
        achievementRepo.save(achievement);
        Log.info("End updateachievement in achievementServImpl");
        return AchievementReqDto.fromEntity(achievementRepo.save(achievement));
    }

    @Override
    public Boolean deleteAchievement(UUID id) {
        Log.info("Start deleteAchievement in AchievementServImpl");

        if (achievementRepo.existsById(id)) {
            achievementRepo.deleteById(id); 
            Log.info("End deleteAchievement in AchievementServImpl");
            return true;
        }
        throw new RuntimeException("Achievement not found");
    }

    @Override
    public List<AchievementReqDto> getAllAchievementEnabled() {
        Log.info("Start getAllAchievementEnabled in AchievementServImpl");
        List<Achievement> response = achievementRepo.findAllByEnabled(1);
        List<AchievementReqDto> achievementReqDto = new ArrayList<>();
        for (Achievement achievement : response) {
            achievementReqDto.add(AchievementReqDto.fromEntity(achievement));
        }
        Log.info("End getAllAchievementEnabled in AchievementServImpl");
        return achievementReqDto;
    }

    @Override
    public long count() {
        return achievementRepo.count(); 
    }

    @Override
    public long countBySearchKeyword(String searchKeyword) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            return achievementRepo.countByAchievementContainingIgnoreCase(searchKeyword);
        }
        return achievementRepo.count(); 
    }
}