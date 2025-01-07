package com.example.lokakarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.lokakarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.lokakarya.Services.AssessmentSummaryServ;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.lokakarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.lokakarya.Entity.Achievement;
import com.example.lokakarya.Entity.EmpAchievementSkill;
import com.example.lokakarya.Entity.User;
import com.example.lokakarya.Repository.AchievementRepo;
import com.example.lokakarya.Repository.EmpAchievementSkillRepo;
import com.example.lokakarya.Repository.UserRepo;
import com.example.lokakarya.Services.EmpAchievementSkillServ;
import com.example.lokakarya.util.GetUserUtil;

@Service
public class EmpAchievementSkillServImpl implements EmpAchievementSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpAchievementSkillServImpl.class);

    @Autowired
    private EmpAchievementSkillRepo empAchievementSkillRepo;
    
    @Autowired
    private AchievementRepo achievementRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Autowired
    AssessmentSummaryServ assessmentSummaryServ;

    @Override
    public List<EmpAchievementSkillReqDto> getAllEmpAchievementSkill() {
       Log.info("Start getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        List<EmpAchievementSkill> response = empAchievementSkillRepo.findAll();
        List<EmpAchievementSkillReqDto> empAchievementSkillReqDto = new ArrayList<>();

         for (EmpAchievementSkill empAchievementSkill : response) {
            empAchievementSkillReqDto.add(EmpAchievementSkillReqDto.fromEntity(empAchievementSkill));
        }
       Log.info("End getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        return empAchievementSkillReqDto;
    }

    @Override
    public EmpAchievementSkillReqDto getEmpAchievementSkillById(UUID id) {
        Log.info("Start getEmpAchievementSkillById in EmpAchievementSkillServImpl");
        EmpAchievementSkill empAchievementSkill = empAchievementSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill not found"));
        Log.info("End getEmpAchievementSkillById in EmpAchievementSkillServImpl");
        return EmpAchievementSkillReqDto.fromEntity(empAchievementSkill);
    }

    @Override
    public EmpAchievementSkillReqDto createEmpAchievementSkill(EmpAchievementSkillDto empAchievementSkillDto) {
        Log.info("Start createEmpAchievementSkill in EmpAchievementSkillServImpl");
        Optional<Achievement> achievementOpt = achievementRepo.findById(empAchievementSkillDto.getAchievementId());
        Optional<User> userOpt = userRepo.findById(empAchievementSkillDto.getUserId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        if (achievementOpt.isEmpty()) {
            throw new RuntimeException("Achievement not found with ID: " + empAchievementSkillDto.getAchievementId());
        }
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + empAchievementSkillDto.getUserId());
        }
        EmpAchievementSkill empAchievementSkill = empAchievementSkillDto.toEntity(
            empAchievementSkillDto,
            achievementOpt.get(),
            userOpt.get(),
            null, 
            null,
            currentUser, 
            new java.util.Date() 
        );
        empAchievementSkill = empAchievementSkillRepo.save(empAchievementSkill);

        UUID userId = empAchievementSkillDto.getUserId();
        int year = empAchievementSkillDto.getAssessmentYear();
        assessmentSummaryServ.calculateAndSaveScoreForUser(userId , year);
        Log.info("End createEmpAchievementSkill in EmpAchievementSkillServImpl");
        return EmpAchievementSkillReqDto.fromEntity(empAchievementSkill);
    }

     @Override
    public List<EmpAchievementSkillReqDto> createAllEmpAchievementSkill(List<EmpAchievementSkillDto> empAchievementSkillDtos) {
        Log.info("Start createAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        List<EmpAchievementSkill> empAchievementSkills = empAchievementSkillDtos.stream().map(empAchievementSkillDto -> {
            Optional<Achievement> achievementOpt = achievementRepo.findById(empAchievementSkillDto.getAchievementId());
            Optional<User> userOpt = userRepo.findById(empAchievementSkillDto.getUserId());
            UUID currentUser = getUserUtil.getCurrentUser().getId();

            if (achievementOpt.isEmpty()) {
                throw new RuntimeException("Achievement not found with ID: " + empAchievementSkillDto.getAchievementId());
            }
            if (userOpt.isEmpty()) {
                throw new RuntimeException("User not found with ID: " + empAchievementSkillDto.getUserId());
            }
            return empAchievementSkillDto.toEntity(
                empAchievementSkillDto,
                achievementOpt.get(),
                userOpt.get(),
                null,
                null,
                currentUser,
                new java.util.Date()
            );
        }).collect(Collectors.toList());
        List<EmpAchievementSkill> savedSkills = empAchievementSkillRepo.saveAll(empAchievementSkills);
         EmpAchievementSkillDto firstDto = empAchievementSkillDtos.get(0);
         UUID userId = firstDto.getUserId();
         int year = firstDto.getAssessmentYear();
         assessmentSummaryServ.calculateAndSaveScoreForUser(userId , year);
        Log.info("End createAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        return savedSkills.stream()
                          .map(EmpAchievementSkillReqDto::fromEntity)
                          .collect(Collectors.toList());
    }


    @Override
    public EmpAchievementSkillReqDto updateEmpAchievementSkill(UUID id, EmpAchievementSkillDto empAchievementSkillDto) {
        Log.info("Start updateEmpAchievementSkill in EmpAchievementSkillServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        EmpAchievementSkill empAchievementSkill = empAchievementSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Achievement Skill not found"));
        empAchievementSkill.setUser(userRepo.findById(empAchievementSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));   
        empAchievementSkill.setNotes(empAchievementSkillDto.getNotes());     
        empAchievementSkill.setAchievement(achievementRepo.findById(empAchievementSkillDto.getAchievementId()).orElseThrow(() -> new RuntimeException("Achievement not found")));
        empAchievementSkill.setScore(empAchievementSkillDto.getScore());
        empAchievementSkill.setAssessmentYear(empAchievementSkillDto.getAssessmentYear());
        empAchievementSkill.setUpdatedBy(currentUser);
        empAchievementSkill.setUpdatedAt(new java.util.Date());
        empAchievementSkillRepo.save(empAchievementSkill);
        UUID userId = empAchievementSkillDto.getUserId();
        int year = empAchievementSkillDto.getAssessmentYear();
        assessmentSummaryServ.calculateAndSaveScoreForUser(userId , year);
        Log.info("End updateEmpAchievementSkill in EmpAchievementSkillServImpl");
        return EmpAchievementSkillReqDto.fromEntity(empAchievementSkillRepo.save(empAchievementSkill));
    }

    @Override
        public Boolean deleteEmpAchievementSkill(UUID id) {
            Log.info("Start deleteEmpAchievementSkill in EmpAchievementSkillServImpl");

            if (empAchievementSkillRepo.existsById(id)) {
                empAchievementSkillRepo.deleteById(id); 
                Log.info("End deleteEmpAchievementSkill in EmpAchievementSkillServImpl");
                return true;
            }
            throw new RuntimeException("EmpAchievementSkill not found");
        }

    @Override
    public List<EmpAchievementSkillReqDto> getAllEmpAchievementSkillByUser(UUID id) {
        Log.info("Start getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        List<EmpAchievementSkill> response = empAchievementSkillRepo.findByUserId(id);
        List<EmpAchievementSkillReqDto> empAchievementSkillReqDto = new ArrayList<>();

        for (EmpAchievementSkill empAchievementSkill : response) {
            empAchievementSkillReqDto.add(EmpAchievementSkillReqDto.fromEntity(empAchievementSkill));
        }
        Log.info("End getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        return empAchievementSkillReqDto;
    }

    @Override
    public List<EmpAchievementSkillReqDto> getAllEmpAchievementSkillByUserAndYear(UUID id, Integer year) {
        Log.info("Start getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        List<EmpAchievementSkill> response = empAchievementSkillRepo.findByUserIdAndAssessmentYear(id, year);
        List<EmpAchievementSkillReqDto> empAchievementSkillReqDto = new ArrayList<>();

        for (EmpAchievementSkill empAchievementSkill : response) {
            if (empAchievementSkill.getAchievement().getGroupAchievement().getEnabled() == 1 && empAchievementSkill.getAchievement().getEnabled() == 1) {
                empAchievementSkillReqDto.add(EmpAchievementSkillReqDto.fromEntity(empAchievementSkill));
            }
        }
        Log.info("End getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        return empAchievementSkillReqDto;
    }

    @Override
    public List<EmpAchievementSkillReqDto> getEmpAchievementSkillByYear(Integer year) {
        Log.info("Start getEmpAchievementSkillByYear in EmpAchievementSkillServImpl");
        List<EmpAchievementSkill> response = empAchievementSkillRepo.findByAssessmentYear(year);
        List<EmpAchievementSkillReqDto> empAchievementSkillReqDto = new ArrayList<>();

        for (EmpAchievementSkill empAchievementSkill : response) {
            empAchievementSkillReqDto.add(EmpAchievementSkillReqDto.fromEntity(empAchievementSkill));
        }
        Log.info("End getEmpAchievementSkillByYear in EmpAchievementSkillServImpl");
        return empAchievementSkillReqDto;
    }

}
