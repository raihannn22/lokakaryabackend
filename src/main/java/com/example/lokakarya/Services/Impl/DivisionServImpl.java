package com.example.lokakarya.Services.Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.Division.DivisionDto;
import com.example.lokakarya.Dto.Division.DivisionReqDto;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.lokakarya.Entity.Division;
import com.example.lokakarya.Entity.TechnicalSkill;
import com.example.lokakarya.Repository.DivisionRepo;
import com.example.lokakarya.Services.DivisionServ;
import com.example.lokakarya.util.GetUserUtil;

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
        Log.info("Start getAllDivision in DivisionServImpl");
        List<Division> response = divisionRepo.findAll();
        List<DivisionReqDto> divisionReqDto = new ArrayList<>();
        for (Division division : response) {
            divisionReqDto.add(DivisionReqDto.fromEntity(division));
        }
        Log.info("End getAllDivision in DivisionServImpl");
        return divisionReqDto;
    }

    @Override
    public List<DivisionReqDto> getPaginatedDivision(int page, int size, String sort, String direction, String searchKeyword) {
        Log.info("Start getPaginatedDivision in DivisionServImpl");
        
        Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<Division> divisionPage;

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            divisionPage = divisionRepo.findByDivisionNameContainingIgnoreCase(searchKeyword, pageable);
        } else {
            divisionPage = divisionRepo.findAll(pageable);
        }

        List<DivisionReqDto> divisionReqDto = new ArrayList<>();
        for (Division Division : divisionPage.getContent()) {
            divisionReqDto.add(DivisionReqDto.fromEntity(Division));
        }
        
        Log.info("End getPaginatedDivision in DivisionServImpl");
        return divisionReqDto;
    }

    @Override
    public DivisionReqDto getDivisionById(UUID id) {
        Log.info("Start getDivisionById in DivisionServImpl");
        Division division = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("Division not found"));
        Log.info("End getDivisionById in DivisionServImpl");
        return DivisionReqDto.fromEntity(division);
    }

    @Override
    public DivisionReqDto createDivision(DivisionDto divisionDto) {
        Log.info("Start createDivision in DivisionServImpl");
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        Division division = divisionDto.toEntity(divisionDto,null,null, currentUserEntity, new java.util.Date());
        divisionRepo.save(division);
        Log.info("End createDivision in DivisionServImpl");
        return DivisionReqDto.fromEntity(divisionRepo.save(division));
    }

    @Override
    public DivisionReqDto updateDivision(UUID id, DivisionDto divisionDto) {
        Log.info("Start updateDivision in DivisionServImpl");
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        Division division = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("Division not found"));
        Division division1 = divisionDto.toEntity(divisionDto, currentUserEntity, new java.util.Date(), division.getCreatedBy(), division.getCreatedAt());
        division1.setId(id);
        Log.info("End updateDivision in DivisionServImpl");
        return DivisionReqDto.fromEntity(divisionRepo.save(division1));
    }

    @Override
    public Boolean deleteDivision(UUID id) {
        Log.info("Start deleteDivision in DivisionServImpl");
        if (!divisionRepo.existsById(id)) throw new RuntimeException("Division not found");
        divisionRepo.deleteById(id);
        Log.info("End deleteDivision in DivisionServImpl");
        return true;
    }

    @Override
    public long count() {
        return divisionRepo.count(); 
    }

    @Override
    public long countBySearchKeyword(String searchKeyword) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            return divisionRepo.countByDivisionNameContainingIgnoreCase(searchKeyword);
        }
        return divisionRepo.count(); 
    }
}
