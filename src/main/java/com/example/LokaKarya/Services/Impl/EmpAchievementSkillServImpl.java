package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.util.GetUserUtil;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.EmpAchievementSkillRepo;
import com.example.LokaKarya.Repository.AchievementRepo;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.EmpAchievementSkillServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public List<EmpAchievementSkillReqDto> getAllEmpAchievementSkill() {
       Log.info("Start getAllEmpAchievementSkill in EmpAchievementSkillServImpl");
        List<EmpAchievementSkill> response = empAchievementSkillRepo.findAll();
        List<EmpAchievementSkillReqDto> empAchievementSkillReqDto = new ArrayList<>();

         for (EmpAchievementSkill empAchievementSkill : response) {
            empAchievementSkillReqDto.add(EmpAchievementSkillReqDto.fromEntity(empAchievementSkill));
        }
       Log.info("End getAllEmpAchievementSkillt in EmpAchievementSkillServImpl");
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

        // Simpan ke repository
        empAchievementSkill = empAchievementSkillRepo.save(empAchievementSkill);

        // Return sebagai DTO
        return EmpAchievementSkillReqDto.fromEntity(empAchievementSkill);
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
        Log.info("End updateEmpAchievementSkill in EmpAchievementSkillServImpl");
        return EmpAchievementSkillReqDto.fromEntity(empAchievementSkillRepo.save(empAchievementSkill));
    }

    @Override
        public Boolean deleteEmpAchievementSkill(UUID id) {
            Log.info("Start deleteEmpAchievementSkill in EmpAchievementSkillServImpl");

            if (empAchievementSkillRepo.existsById(id)) {
                empAchievementSkillRepo.deleteById(id);  // hanya menghapus achievement berdasarkan id
                Log.info("End deleteEmpAchievementSkill in EmpAchievementSkillServImpl");
                return true;
            }
            throw new RuntimeException("EmpAchievementSkill not found");
        }
}
