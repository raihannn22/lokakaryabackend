package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionDto;
import com.example.LokaKarya.Dto.EmpSuggestion.EmpSuggestionReqDto;
import com.example.LokaKarya.Entity.EmpSuggestion;
import com.example.LokaKarya.Repository.EmpSuggestionRepo;
import com.example.LokaKarya.Services.EmpSuggestionServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpSuggestionServImpl implements EmpSuggestionServ {

    private final Logger Log = LoggerFactory.getLogger(EmpSuggestionServImpl.class);

    @Autowired
    private EmpSuggestionRepo empSuggestionRepo;

    @Override
    public List<EmpSuggestionDto> getAllEmpSuggestion() {
       Log.info("Start getAllEmpSuggestion in EmpSuggestionServImpl");
        List<EmpSuggestion> response = empSuggestionRepo.findAll();
        List<EmpSuggestionDto> empSuggestionList = new ArrayList<>();

        for (EmpSuggestion empSuggestion : response) {
            EmpSuggestionDto empSuggestionDto = EmpSuggestionDto.fromEntity(empSuggestion);
            empSuggestionList.add(empSuggestionDto);
        }
       Log.info("End getAllEmpSuggestiont in EmpSuggestionServImpl");
        return empSuggestionList;
    }

    @Override
    public EmpSuggestionDto getEmpSuggestionById(UUID id) {
       Log.info("Start getEmpSuggestionById in EmpSuggestionServImpl");
        Optional<EmpSuggestion> empSuggestion = empSuggestionRepo.findById(id);
       Log.info("End getEmpSuggestionById in EmpSuggestionServImpl");
        return empSuggestion.map(EmpSuggestionDto::fromEntity).orElse(null);
    }

    @Override
    public EmpSuggestionDto createEmpSuggestion (EmpSuggestionReqDto empSuggestiontDto) {
       Log.info("Start createGroupAchievement in GroupAchievementServImpl");

        EmpSuggestion empSuggestion = EmpSuggestionReqDto.toEntity(empSuggestiontDto);

        empSuggestionRepo.save(empSuggestion);
       Log.info("End createGroupAchievement in GroupAchievementServImpl");
        return EmpSuggestionDto.fromEntity(empSuggestion);
    }

    @Override
    public EmpSuggestionDto updateEmpSuggestion (UUID id, EmpSuggestionReqDto empSuggestionDto) {
       Log.info("Start updateEmpSuggestion in EmpSuggestionServImpl");
        EmpSuggestion findEmpSuggestion  = empSuggestionRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Technical Skill"));

        updateEmpSuggestionFields(findEmpSuggestion , empSuggestionDto);

        empSuggestionRepo.save(findEmpSuggestion);
       Log.info("End updateEmpSuggestion in EmpSuggestionServImpl");
        return EmpSuggestionDto.fromEntity(findEmpSuggestion);
    }

    @Override
    public Boolean deleteEmpSuggestion(UUID id) {
       Log.info("Start deleteEmpSuggestion in EmpSuggestionServImpl");
        EmpSuggestion findEmpSuggestion = empSuggestionRepo.findById(id).orElseThrow(() -> new RuntimeException("Emp Achievement Skill Achievement not found"));
        empSuggestionRepo.delete(findEmpSuggestion);
       Log.info("End deleteEmpSuggestion in EmpSuggestionServImpl");
        return true;
    }

    private void updateEmpSuggestionFields(EmpSuggestion existingEmpSuggestion, EmpSuggestionReqDto empSuggestionDto) {
        if (empSuggestionDto.getUserId() != null) {
            existingEmpSuggestion.setUserId(empSuggestionDto.getUserId());
        }
        if (empSuggestionDto.getSuggestion() != null) {
            existingEmpSuggestion.setSuggestion(empSuggestionDto.getSuggestion());
        }
        if (empSuggestionDto.getAssessmentYear() != null) {
            existingEmpSuggestion.setAssessmentYear(empSuggestionDto.getAssessmentYear());
        }
        
        

    }
}
