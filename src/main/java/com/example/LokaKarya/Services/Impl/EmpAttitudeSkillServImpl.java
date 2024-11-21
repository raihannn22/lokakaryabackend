package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Config.GetUserUtil;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillDto;
import com.example.LokaKarya.Dto.EmpAchievementSkill.EmpAchievementSkillReqDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillDto;
import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.EmpAchievementSkill;
import com.example.LokaKarya.Entity.EmpAttitudeSkill;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.EmpAttitudeSkillRepo;
import com.example.LokaKarya.Repository.AttitudeSkillRepo;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.EmpAttitudeSkillServ;

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

import com.example.LokaKarya.Entity.AttitudeSkill;

@Service
public class EmpAttitudeSkillServImpl implements EmpAttitudeSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpAttitudeSkillServImpl.class);

    @Autowired
    private EmpAttitudeSkillRepo empAttitudeSkillRepo;

    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<EmpAttitudeSkillReqDto> getAllEmpAttitudeSkill() {
       Log.info("Start getAllEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        List<EmpAttitudeSkill> response = empAttitudeSkillRepo.findAll();
        List<EmpAttitudeSkillReqDto> empAttitudeSkillReqDto = new ArrayList<>();

        for (EmpAttitudeSkill empAttitudeSkill : response) {
            empAttitudeSkillReqDto.add(EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkill));
        }
       Log.info("End getAllEmpAttitudeSkillt in EmpAttitudeSkillServImpl");
        return empAttitudeSkillReqDto;
    }

    @Override
    public EmpAttitudeSkillReqDto getEmpAttitudeSkillById(UUID id) {
        Log.info("Start getEmpAttitudeSkillById in EmpAttitudeSkillServImpl");
        EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Attitude Skill not found"));
        Log.info("End getEmpAttitudeSkillById in EmpAttitudeSkillServImpl");
        return EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkill);
    }

    @Override
    public EmpAttitudeSkillReqDto createEmpAttitudeSkill(EmpAttitudeSkillDto empAttitudeSkillDto) {
        // Cari attitudeSkill berdasarkan ID
        Optional<AttitudeSkill> attitudeSkillOpt = attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId());

        // Cari user berdasarkan ID
        Optional<User> userOpt = userRepo.findById(empAttitudeSkillDto.getUserId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();

        // Validasi keberadaan attitudeSkill
        if (attitudeSkillOpt.isEmpty()) {
            throw new RuntimeException("AttitudeSkill not found with ID: " + empAttitudeSkillDto.getAttitudeSkillId());
        }

        // Validasi keberadaan user
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + empAttitudeSkillDto.getUserId());
        }

        // Konversi DTO ke entity
        EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillDto.toEntity(
            empAttitudeSkillDto,
            attitudeSkillOpt.get(),
            userOpt.get(),
            null,
            null, 
            currentUser, 
            new java.util.Date() 
        );

        // Tambahkan user ke entity jika relevan
        empAttitudeSkill.setUser(userOpt.get());

        // Simpan ke repository
        empAttitudeSkill = empAttitudeSkillRepo.save(empAttitudeSkill);

        // Return sebagai DTO
        return EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkill);
    }


    @Override
    public EmpAttitudeSkillReqDto updateEmpAttitudeSkill(UUID id, EmpAttitudeSkillDto empAttitudeSkillDto) {
        Log.info("Start updateEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        EmpAttitudeSkill empAttitudeSkill = empAttitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Emp Attitude Skill not found"));
        empAttitudeSkill.setUser(userRepo.findById(empAttitudeSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));    
        empAttitudeSkill.setAttitudeSkill(attitudeSkillRepo.findById(empAttitudeSkillDto.getAttitudeSkillId()).orElseThrow(() -> new RuntimeException("Attitude Skill not found")));
        empAttitudeSkill.setScore(empAttitudeSkillDto.getScore());
        empAttitudeSkill.setAssessmentYear(empAttitudeSkillDto.getAssessmentYear());
        empAttitudeSkill.setUpdatedBy(currentUser);
        empAttitudeSkill.setUpdatedAt(new java.util.Date());
        empAttitudeSkillRepo.save(empAttitudeSkill);
        Log.info("End updateEmpAttitudeSkill in EmpAttitudeSkillServImpl");
        return EmpAttitudeSkillReqDto.fromEntity(empAttitudeSkillRepo.save(empAttitudeSkill));
    }

    @Override
        public Boolean deleteEmpAttitudeSkill(UUID id) {
            Log.info("Start deleteEmpAttitudeSkill in EmpAttitudeSkillServImpl");

            if (empAttitudeSkillRepo.existsById(id)) {
                empAttitudeSkillRepo.deleteById(id);  // hanya menghapus Attitude berdasarkan id
                Log.info("End deleteEmpAttitudeSkill in EmpAttitudeSkillServImpl");
                return true;
            }
            throw new RuntimeException("EmpAttitudeSkill not found");
        }
}
