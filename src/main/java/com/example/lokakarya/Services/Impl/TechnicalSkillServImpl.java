package com.example.lokakarya.Services.Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillDto;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.lokakarya.Entity.TechnicalSkill;
import com.example.lokakarya.Repository.TechnicalSkillRepo;
import com.example.lokakarya.Services.TechnicalSkillServ;
import com.example.lokakarya.util.GetUserUtil;

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
    public List<TechnicalSkillReqDto> getPaginatedTechnicalSkill(int page, int size, String sort, String direction, String searchKeyword) {
        Log.info("Start getPaginatedTechnicalSkill in TechnicalSkillServImpl");
        Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<TechnicalSkill> technicalSkillPage;
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            technicalSkillPage = technicalSkillRepo.findByTechnicalSkillContainingIgnoreCase(searchKeyword, pageable);
        } else {
            technicalSkillPage = technicalSkillRepo.findAll(pageable);
        }
        List<TechnicalSkillReqDto> technicalSkillReqDto = new ArrayList<>();
        for (TechnicalSkill technicalSkill : technicalSkillPage.getContent()) {
            technicalSkillReqDto.add(TechnicalSkillReqDto.fromEntity(technicalSkill));
        }
        Log.info("End getPaginatedTechnicalSkill in TechnicalSkillServImpl");
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
        Log.info("Start createAttitudeSkill in AttitudeSkillServImpl");
            TechnicalSkill technicalSkill = technicalSkillDto.toEntity(technicalSkillDto, null, null, null, new java.util.Date());
            technicalSkillRepo.save(technicalSkill);
            Log.info("End createAttitudeSkill in AttitudeSkillServImpl");
            return TechnicalSkillReqDto.fromEntity(technicalSkillRepo.save(technicalSkill));
    }

    @Override
    public TechnicalSkillReqDto updateTechnicalSkill(UUID id, TechnicalSkillDto technicalSkillDto) {
        Log.info("Start updateAttitudeSkill in AttitudeSkillServImpl");
        TechnicalSkill technicalSkill = technicalSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TechnicalSkill not found"));
        technicalSkill.setTechnicalSkill(technicalSkillDto.getTechnicalSkill());
        technicalSkill.setEnabled(technicalSkillDto.getEnabled());
        technicalSkill.setUpdatedAt(new java.util.Date());
        technicalSkillRepo.save(technicalSkill);
        Log.info("End updateAttitudeSkill in AttitudeSkillServImpl");
        return TechnicalSkillReqDto.fromEntity(technicalSkillRepo.save(technicalSkill));
    }

    @Override
    public Boolean deleteTechnicalSkill(UUID id) {
        Log.info("Start deleteTechnicalSkill in TechnicalSkillServImpl");
        if (technicalSkillRepo.existsById(id)) {
            technicalSkillRepo.deleteById(id);  
            Log.info("End deleteTechnicalSkill in TechnicalSkillServImpl");
            return true;
        }
        throw new RuntimeException("TechnicalSkill not found");
    }

    @Override
    public long count() {
        return technicalSkillRepo.count(); 
    }

    @Override
    public long countBySearchKeyword(String searchKeyword) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            return technicalSkillRepo.countByTechnicalSkillContainingIgnoreCase(searchKeyword);
        }
        return technicalSkillRepo.count(); 
    }
}