package com.example.LokaKarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillDto;
import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.LokaKarya.Entity.TechnicalSkill;
import com.example.LokaKarya.Repository.TechnicalSkillRepo;
import com.example.LokaKarya.Services.TechnicalSkillServ;
import com.example.LokaKarya.util.GetUserUtil;

@Service
public class TechnicalSkillServImpl implements TechnicalSkillServ {

    private final Logger Log = LoggerFactory.getLogger(TechnicalSkillServImpl.class);

    @Autowired
    private TechnicalSkillRepo technicalSkillRepo;

    @Autowired
    GetUserUtil getUserUtil;


    @Override
    public List<TechnicalSkillReqDto> getAllTechnicalSkill() {
        Log.info("Start getAllTechnicalSkill in TechnicalSkillServImpl");
        List<TechnicalSkill> response = technicalSkillRepo.findAll();
        List<TechnicalSkillReqDto> technicalSkillReqDto = new ArrayList<>();

        for (TechnicalSkill technicalSkill : response) {
            technicalSkillReqDto.add(TechnicalSkillReqDto.fromEntity(technicalSkill));
        }
        Log.info("End getAllTechnicalSkill in TechnicalSkillServImpl");
        return technicalSkillReqDto;
    }

    @Override
    public TechnicalSkillReqDto getTechnicalSkillById(UUID id) {
        Log.info("Start getTechnicalSkillById in TechnicalSkillServImpl");
        TechnicalSkill technicalSkill = technicalSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("TechnicalSkill not found"));
        Log.info("End getAttitudeSkillById in AttitudeSkillServImpl");
        return TechnicalSkillReqDto.fromEntity(technicalSkill);
    }

    @Override
    public TechnicalSkillReqDto createTechnicalSkill(TechnicalSkillDto technicalSkillDto) {
        // UUID currentUser = getUserUtil.getCurrentUser().getId();
        
            TechnicalSkill technicalSkill = technicalSkillDto.toEntity(technicalSkillDto, null, null, null, new java.util.Date());
            technicalSkillRepo.save(technicalSkill);
            return TechnicalSkillReqDto.fromEntity(technicalSkillRepo.save(technicalSkill));
    }

    @Override
    public TechnicalSkillReqDto updateTechnicalSkill(UUID id, TechnicalSkillDto technicalSkillDto) {
        Log.info("Start updateAttitudeSkill in AttitudeSkillServImpl");
        // UUID currentUser = getUserUtil.getCurrentUser().getId();
        TechnicalSkill technicalSkill = technicalSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TechnicalSkill not found"));
        technicalSkill.setTechnicalSkill(technicalSkillDto.getTechnicalSkill());
        technicalSkill.setEnabled(technicalSkillDto.getEnabled());
        // technicalSkill.setUpdatedBy(currentUser);
        technicalSkill.setUpdatedAt(new java.util.Date());
        technicalSkillRepo.save(technicalSkill);
        Log.info("End updateAttitudeSkill in AttitudeSkillServImpl");
        return TechnicalSkillReqDto.fromEntity(technicalSkillRepo.save(technicalSkill));
    }

    @Override
    public Boolean deleteTechnicalSkill(UUID id) {
        Log.info("Start deleteTechnicalSkill in TechnicalSkillServImpl");

        if (technicalSkillRepo.existsById(id)) {
            technicalSkillRepo.deleteById(id);  // hanya menghapus TechnicalSkill berdasarkan id
            Log.info("End deleteTechnicalSkill in TechnicalSkillServImpl");
            return true;
        }
        throw new RuntimeException("TechnicalSkill not found");
    }


}
