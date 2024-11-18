package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillDto;
import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.LokaKarya.Entity.TechnicalSkill;
import com.example.LokaKarya.Repository.TechnicalSkillRepo;
import com.example.LokaKarya.Services.TechnicalSkillServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TechnicalSkillServImpl implements TechnicalSkillServ {

    private final Logger Log = LoggerFactory.getLogger(TechnicalSkillServImpl.class);

    @Autowired
    private TechnicalSkillRepo technicalSkillRepo;


    @Override
    public List<TechnicalSkillDto> getAllTechnicalSkill() {
        Log.info("Start getAllTechnicalSkill in TechnicalSkillServImpl");
        List<TechnicalSkill> response = technicalSkillRepo.findAll();
        List<TechnicalSkillDto> technicalSkillList = new ArrayList<>();

        for (TechnicalSkill technicalSkill : response) {
            TechnicalSkillDto technicalSkillDto = TechnicalSkillDto.fromEntity(technicalSkill);
            technicalSkillList.add(technicalSkillDto);
        }
        Log.info("End getAllTechnicalSkill in TechnicalSkillServImpl");
        return technicalSkillList;
    }

    @Override
    public TechnicalSkillDto getTechnicalSkillById(UUID id) {
        Log.info("Start getTechnicalSkillById in TechnicalSkillServImpl");
        Optional<TechnicalSkill> technicalSkill = technicalSkillRepo.findById(id);
        Log.info("End getTechnicalSkillById in TechnicalSkillServImpl");
        return technicalSkill.map(TechnicalSkillDto::fromEntity).orElse(null);
    }

    @Override
    public TechnicalSkillDto createTechnicalSkill(TechnicalSkillReqDto technicalSkillDto) {
        Log.info("Start createTechnicalSkill in TechnicalSkillServImpl");

        TechnicalSkill technicalSkill = TechnicalSkillReqDto.toEntity(technicalSkillDto);

        technicalSkillRepo.save(technicalSkill);
        Log.info("End createTechnicalSkill in TechnicalSkillServImpl");
        return TechnicalSkillDto.fromEntity(technicalSkill);
    }

    @Override
    public TechnicalSkillDto updateTechnicalSkill(UUID id, TechnicalSkillReqDto technicalSkillDto) {
        Log.info("Start updateTechnicalSkill in TechnicalSkillServImpl");
        TechnicalSkill findTechnicalSkill = technicalSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Technical Skill not found"));

        updateTechnicalSkillFields(findTechnicalSkill, technicalSkillDto);

        technicalSkillRepo.save(findTechnicalSkill);
        Log.info("End updateTechnicalSkill in TechnicalSkillServImpl");
        return TechnicalSkillDto.fromEntity(findTechnicalSkill);
    }

    @Override
    public Boolean deleteTechnicalSkill(UUID id) {
        Log.info("Start deleteTechnicalSkill in TechnicalSkillServImpl");
        TechnicalSkill findTechnicalSkill = technicalSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Technical Skill not found"));
        technicalSkillRepo.delete(findTechnicalSkill);
        Log.info("End deleteTechnicalSkill in TechnicalSkillServImpl");
        return true;
    }

    // KONTOLODON
    private void updateTechnicalSkillFields(TechnicalSkill existingTechnicalSkill, TechnicalSkillReqDto TechnicalSkillDto) {
        if (TechnicalSkillDto.getTechnicalSkill() != null) {
            existingTechnicalSkill.setTechnicalSkill(TechnicalSkillDto.getTechnicalSkill());
        }
        if (TechnicalSkillDto.getEnabled() != null) {
            existingTechnicalSkill.setEnabled(TechnicalSkillDto.getEnabled());
        }
        

    }
}
