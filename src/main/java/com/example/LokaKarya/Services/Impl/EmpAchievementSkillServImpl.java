package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.example.LokaKarya.Entity.TechnicalSkill;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Repository.EmpAchievementSkillRepo;
import com.example.LokaKarya.Repository.AchievementRepo;
import com.example.LokaKarya.Services.EmpAchievementSkillServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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
        Optional<Achievement> achievement = achievementRepo.findById(empAchievementSkillDto.getAchievementId());
        if (achievement.isPresent()) {
            EmpAchievementSkill empAchievementSkill = empAchievementSkillDto.toEntity(empAchievementSkillDto, achievement.get(), UUID.randomUUID(), Date.valueOf(LocalDate.now()), achievement.get().getId(), Date.valueOf(LocalDate.now()));
           empAchievementSkillRepo.save(empAchievementSkill);
            return EmpAchievementSkillReqDto.fromEntity(empAchievementSkillRepo.save(empAchievementSkill));
        }else {
            throw new RuntimeException("EmpAchievementSkill not found");
        }
    }

    @Override
    public EmpAchievementSkillReqDto updateEmpAchievementSkill(UUID id, EmpAchievementSkillDto empAchievementSkillDto) {
        Log.info("Start updateEmpAchievementSkill in EmpAchievementSkillServImpl");

        EmpAchievementSkill empAchievementSkill = empAchievementSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Achievement Skill not found"));
        empAchievementSkill.setUserId(empAchievementSkillDto.getUserId());     
        empAchievementSkill.setNotes(empAchievementSkillDto.getNotes());     
        empAchievementSkill.setAchievement(achievementRepo.findById(empAchievementSkillDto.getAchievementId()).orElseThrow(() -> new RuntimeException("Achievement Skill not found")));
        empAchievementSkill.setScore(empAchievementSkillDto.getScore());
        empAchievementSkill.setAssessmentYear(empAchievementSkillDto.getAssessmentYear());
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

    // private void updateEmpAchievementSkillFields(EmpAchievementSkill existingEmpAchievementSkill, EmpAchievementSkillReqDto empAchievementSkillDto) {
    //     if (empAchievementSkillDto.getUserId() != null) {
    //         existingEmpAchievementSkill.setUserId(empAchievementSkillDto.getUserId());
    //     }
    //     if (empAchievementSkillDto.getNotes() != null) {
    //         existingEmpAchievementSkill.setNotes(empAchievementSkillDto.getNotes());
    //     }
    //     if (empAchievementSkillDto.getAchievementId() != null) {
    //         existingEmpAchievementSkill.setAchievementId(empAchievementSkillDto.getAchievementId());
    //     }
    //     if (empAchievementSkillDto.getScore() != null) {
    //         existingEmpAchievementSkill.setScore(empAchievementSkillDto.getScore());
    //     }
    //     if (empAchievementSkillDto.getAssessmentYear() != null) {
    //         existingEmpAchievementSkill.setAssessmentYear(empAchievementSkillDto.getAssessmentYear());
    //     }
        
        

    // }
}
