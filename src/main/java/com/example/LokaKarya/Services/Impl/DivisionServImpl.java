package com.example.LokaKarya.Services.Impl;

// import com.example.LokaKarya.Dto.AppRole.AppRoleReqDto;
// import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.LokaKarya.util.GetUserUtil;
import com.example.LokaKarya.Dto.Division.DivisionDto;
import com.example.LokaKarya.Dto.Division.DivisionReqDto;
// import com.example.LokaKarya.Entity.AppRole;
// import com.example.LokaKarya.Entity.AssessmentSummary;
import com.example.LokaKarya.Entity.Division;
// import com.example.LokaKarya.Repository.AssessmentSummaryRepo;
import com.example.LokaKarya.Repository.DivisionRepo;
import com.example.LokaKarya.Services.DivisionServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DivisionServImpl implements DivisionServ {
    private final Logger Log = LoggerFactory.getLogger(DivisionServImpl.class);

    @Autowired
    DivisionRepo divisionRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<DivisionReqDto> getAllDivision() {
        List<Division> response = divisionRepo.findAll();
        List<DivisionReqDto> divisionReqDto = new ArrayList<>();
        for (Division division : response) {
            divisionReqDto.add(DivisionReqDto.fromEntity(division));
        }
        return divisionReqDto;
    }

    @Override
    public DivisionReqDto getDivisionById(UUID id) {
        Division division = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("Division not found"));
        return DivisionReqDto.fromEntity(division);
    }

    @Override
    public DivisionReqDto createDivision(DivisionDto divisionDto) {
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        Division division = divisionDto.toEntity(divisionDto,null,null, currentUserEntity, new java.util.Date());
        divisionRepo.save(division);
        return DivisionReqDto.fromEntity(divisionRepo.save(division));
    }

    @Override
    public DivisionReqDto updateDivision(UUID id, DivisionDto divisionDto) {
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        Division division = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("Division not found"));
        Division division1 = divisionDto.toEntity(divisionDto, currentUserEntity, new java.util.Date(), division.getCreatedBy(), division.getCreatedAt());
        division1.setId(id);
        return DivisionReqDto.fromEntity(divisionRepo.save(division1));
    }

    @Override
    public Boolean deleteDivision(UUID id) {
        if (!divisionRepo.existsById(id)) throw new RuntimeException("Division not found");
        divisionRepo.deleteById(id);
        return true;
    }
}
