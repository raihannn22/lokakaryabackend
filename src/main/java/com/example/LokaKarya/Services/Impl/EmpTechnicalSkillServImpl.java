package com.example.LokaKarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.Dto.EmpAttitudeSkill.EmpAttitudeSkillReqDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillDto;
import com.example.LokaKarya.Dto.EmpTechnicalSkill.EmpTechnicalSkillReqDto;
import com.example.LokaKarya.Entity.EmpAttitudeSkill;
import com.example.LokaKarya.Entity.EmpTechnicalSkill;
import com.example.LokaKarya.Entity.TechnicalSkill;
import com.example.LokaKarya.Entity.User;
import com.example.LokaKarya.Repository.EmpTechnicalSkillRepo;
import com.example.LokaKarya.Repository.TechnicalSkillRepo;
import com.example.LokaKarya.Repository.UserRepo;
import com.example.LokaKarya.Services.EmpTechnicalSkillServ;
import com.example.LokaKarya.util.GetUserUtil;

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
    public List<EmpTechnicalSkillReqDto> getEmpTechnicalSkillByUserId(UUID userId) {
        Log.info("Start getEmpTechnicalSkillByUserId in EmpTechnicalSkillServImpl");

        // Ambil semua data berdasarkan userId
        List<EmpTechnicalSkill> empTechnicalSkillList = empTechnicalSkillRepo.findByUserId(userId);

        // Konversi ke DTO
        List<EmpTechnicalSkillReqDto> empTechnicalSkillDtos = empTechnicalSkillList.stream()
            .map(EmpTechnicalSkillReqDto::fromEntity)
            .collect(Collectors.toList());

        Log.info("End getEmpTechnicalSkillByUserId in EmpTechnicalSkillServImpl");
        return empTechnicalSkillDtos;
    }

        @Override
        public List<EmpTechnicalSkillReqDto> getEmpTechnicalSkillsByUserIdAndYear(UUID userId, Integer assessmentYear) {
            Log.info("Start getEmpTechnicalSkillByUserIdAndYear in EmpTechnicalSkillServImpl");

            // Ambil semua data berdasarkan userId
            List<EmpTechnicalSkill> empTechnicalSkillList = empTechnicalSkillRepo.findByUserIdAndAssessmentYear(userId, assessmentYear);

            // Konversi ke DTO
            List<EmpTechnicalSkillReqDto> empTechnicalSkillDtos = empTechnicalSkillList.stream()
                .map(EmpTechnicalSkillReqDto::fromEntity)
                .collect(Collectors.toList());

            Log.info("End getEmpTechnicalSkillByUserIdAndYear in EmpTechnicalSkillServImpl");
            return empTechnicalSkillDtos;
        }
        @Override
        public List<EmpTechnicalSkillReqDto> createAllEmpTechnicalSkill(List<EmpTechnicalSkillDto> empTechnicalSkillDtos) {
            List<EmpTechnicalSkill> empTechnicalSkills = empTechnicalSkillDtos.stream().map(empTechnicalSkillDto -> {
                // Mengambil TechnicalSkill berdasarkan ID yang diberikan
                Optional<TechnicalSkill> technicalSkillOpt = technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId());
                Optional<User> userOpt = userRepo.findById(empTechnicalSkillDto.getUserId());
                // Mendapatkan user_id dari current user
                UUID currentUser = getUserUtil.getCurrentUser().getId();

                // Memeriksa jika Technical skill tidak ditemukan
                if (technicalSkillOpt.isEmpty()) {
                    throw new RuntimeException("Technical Skill not found with ID: " + empTechnicalSkillDto.getTechnicalSkillId());
                }
                if (userOpt.isEmpty()) {
                        throw new RuntimeException("User not found with ID: " + empTechnicalSkillDto.getUserId());
                }

                // Menggunakan currentUser sebagai user_id
                return empTechnicalSkillDto.toEntity(
                    empTechnicalSkillDto,
                    technicalSkillOpt.get(),
                    userOpt.get(), 
                    null,
                    null,
                    currentUser,
                    new java.util.Date() 
                );
            }).collect(Collectors.toList());

            // Simpan semua EmpTechnicalSkill
            List<EmpTechnicalSkill> savedSkills = empTechnicalSkillRepo.saveAll(empTechnicalSkills);

            // Kembalikan hasil sebagai DTO
            return savedSkills.stream()
                            .map(EmpTechnicalSkillReqDto::fromEntity)
                            .collect(Collectors.toList());
        
        }

    }


// @Override
// public EmpTechnicalSkillReqDto updateEmpTechnicalSkill(UUID id, EmpTechnicalSkillDto empTechnicalSkillDto) {
//     Log.info("Start updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
//     UUID currentUser = getUserUtil.getCurrentUser().getId();
//     EmpTechnicalSkill empTechnicalSkill = empTechnicalSkillRepo.findById(id)
//             .orElseThrow(() -> new RuntimeException("Emp Technical Skill not found"));
//     empTechnicalSkill.setUser(userRepo.findById(empTechnicalSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));    
//     empTechnicalSkill.setTechnicalSkill(technicalSkillRepo.findById(empTechnicalSkillDto.getTechnicalSkillId()).orElseThrow(() -> new RuntimeException("Technical Skill not found")));
//     empTechnicalSkill.setScore(empTechnicalSkillDto.getScore());
//     empTechnicalSkill.setAssessmentYear(empTechnicalSkillDto.getAssessmentYear());
//     empTechnicalSkill.setUpdatedBy(currentUser);
//     empTechnicalSkill.setUpdatedAt(new java.util.Date());
//     empTechnicalSkillRepo.save(empTechnicalSkill);
//     Log.info("End updateEmpTechnicalSkill in EmpTechnicalSkillServImpl");
//     return EmpTechnicalSkillReqDto.fromEntity(empTechnicalSkillRepo.save(empTechnicalSkill));
// }

//     @Override
//     public Boolean deleteEmpTechnicalSkill(UUID id) {
//         Log.info("Start deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");

//         if (empTechnicalSkillRepo.existsById(id)) {
//             empTechnicalSkillRepo.deleteById(id);  // hanya menghapus achievement berdasarkan id
//             Log.info("End deleteEmpTechnicalSkill in EmpTechnicalSkillServImpl");
//             return true;
//         }
//         throw new RuntimeException("EmpTechnicalSkill not found");
//     }