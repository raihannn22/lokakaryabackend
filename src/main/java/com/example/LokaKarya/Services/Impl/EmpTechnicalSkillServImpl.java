package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.util.GetUserUtil;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.LokaKarya.Entity.TechnicalSkill;
import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.EmpTechnicalSkillRepo;
import com.example.LokaKarya.Repository.TechnicalSkillRepo;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.EmpTechnicalSkillServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpTechnicalSkillServImpl implements EmpTechnicalSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpTechnicalSkillServImpl.class);

    @Autowired
    private EmpTechnicalSkillRepo empTechnicalSkillRepo;

    @Autowired
    private TechnicalSkillRepo technicalSkillRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<EmpTechnicalSkillReqDto> getAllEmpTechnicalSkill() {
       Log.info("Start getAllEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        List<EmpTechnicalSkill> response = empTechnicalSkillRepo.findAll();
        List<EmpTechnicalSkillReqDto> empTechnicalSkillReqDto = new ArrayList<>();

        for (EmpTechnicalSkill empTechnicalSkill : response) {
            empTechnicalSkillReqDto.add(EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkill));
        }
       Log.info("End getAllEmpTechnicalSkillt in EmpTechnicalSkillServImpl");
        return empTechnicalSkillReqDto;
    }

    @Override
    public EmpTechnicalSkillReqDto getEmpTechnicalSkillById(UUID id) {
        Log.info("Start getEmpTechnicalSkillById in EmpTechnicalSkillServImpl");
        EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Technical Skill not found"));
        Log.info("End getEmpTechnicalSkillById in EmpTechnicalSkillServImpl");
        return EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkill);
    }


    @Override
    public EmpTechnicalSkillReqDto createEmpTechnicalSkill(EmpTechnicalSkillDto empTechnicalSkillDto) {
        // Cari technical skill berdasarkan ID
        Optional<TechnicalSkill> technicalSkillOpt = technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId());
        // Cari user berdasarkan ID
        Optional<User> userOpt = userRepo.findById(empTechnicalSkillDto.getUserId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();

        // Validasi keberadaan technical skill dan user
        if (technicalSkillOpt.isEmpty()) {
            throw new RuntimeException("Technical Skill not found with ID: " + empTechnicalSkillDto.getTechnicalSkillId());
        }
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + empTechnicalSkillDto.getUserId());
        }

        // Konversi DTO ke entity
        EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillDto.toEntity(
            empTechnicalSkillDto,
            technicalSkillOpt.get(),
            userOpt.get(),
            null, 
            null,
            currentUser, 
            new java.util.Date() 
        );

        // Simpan entity ke repository
        EmpTechnicalSkill savedEntity = empTechnicalSkillRepo.save(empTechnicalSkill);

        // Konversi entity ke response DTO
        return EmpTechnicalSkillReqDto.fromEntity(savedEntity);
    }


    @Override
    public EmpTechnicalSkillReqDto updateEmpTechnicalSkill(UUID id, EmpTechnicalSkillDto empTechnicalSkillDto) {
        Log.info("Start updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Technical Skill not found"));
        empTechnicalSkill.setUser(userRepo.findById(empTechnicalSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));    
        empTechnicalSkill.setTechnicalSkill(technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId()).orElseThrow(() -> new RuntimeException("Technical Skill not found")));
        empTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
        empTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
        empTechnicalSkill.setUpdatedBy(currentUser);
        empTechnicalSkill.setUpdatedAt(new java.util.Date());
        empTechnicalSkillRepo.save(empTechnicalSkill);
        Log.info("End updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
        return EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkillRepo.save(empTechnicalSkill));
    }

        @Override
        public Boolean deleteEmpTechnicalSkill(UUID id) {
            Log.info("Start deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");

            if (empTechnicalSkillRepo.existsById(id)) {
                empTechnicalSkillRepo.deleteById(id);  // hanya menghapus achievement berdasarkan id
                Log.info("End deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");
                return true;
            }
            throw new RuntimeException("EmpTechnicalSkill not found");
        }

    // private void updateEmpTechnicalSkillFields(EmpTechnicalSkill existingEmpTechnicalSkill, EmpTechnicalSkillReqDto empTechnicalSkillDto) {
    //     if (empTechnicalSkillDto.getUserId() != null) {
    //         existingEmpTechnicalSkill.setUserId(empTechnicalSkillDto.getUserId());
    //     }
    //     if (empTechnicalSkillDto.getTechnicalSkillId() != null) {
    //         existingEmpTechnicalSkill.setTechnicalSkillId(empTechnicalSkillDto.getTechnicalSkillId());
    //     }
    //     if (empTechnicalSkillDto.getScore() != null) {
    //         existingEmpTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
    //     }
    //     if (empTechnicalSkillDto.getAssessmentYear() != null) {
    //         existingEmpTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
    //     }
        
        

    // }
}
