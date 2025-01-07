package com.example.lokakarya.Services.Impl;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.lokakarya.Dto.AttitudeSkill.AttitudeSkillDto;
import com.example.lokakarya.Dto.AttitudeSkill.AttitudeSkillReqDto;
import com.example.lokakarya.Entity.AttitudeSkill;
import com.example.lokakarya.Entity.GroupAttitudeSkill;
import com.example.lokakarya.Repository.AttitudeSkillRepo;
import com.example.lokakarya.Repository.GroupAttitudeSkillRepo;
import com.example.lokakarya.Services.AttitudeSkillServ;
import com.example.lokakarya.util.GetUserUtil;

@Service
public class AttitudeSkillServImpl implements AttitudeSkillServ {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryServImpl.class);

    @Autowired
    AttitudeSkillRepo attitudeSkillRepo;

    @Autowired
    GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<AttitudeSkillReqDto> getAllAttitudeSkill() {
        Log.info("Start getAllAttitudeSkill in AttitudeSkillServImpl");
        List<AttitudeSkill> response = attitudeSkillRepo.findAll();
        List<AttitudeSkillReqDto> attitudeSkillReqDto = new ArrayList<>();
        for (AttitudeSkill attitudeSkill : response) {
            attitudeSkillReqDto.add(AttitudeSkillReqDto.fromEntity(attitudeSkill));
        }
        Log.info("End getAllAttitudeSkill in AttitudeSkillServImpl");
        return attitudeSkillReqDto;
    }

    @Override
    public List<AttitudeSkillReqDto> getPaginatedAttitudeSkill(int page, int size, String sort, String direction, String searchKeyword) {
        Log.info("Start getPaginatedAttitudeSkill in AttitudeSkillServImpl");
        Sort sorting = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sorting);
        Page<AttitudeSkill> attitudeSkillPage;
        if (searchKeyword != null && !searchKeyword.isEmpty()) {            
            attitudeSkillPage = attitudeSkillRepo.findByAttitudeSkillContainingIgnoreCase(searchKeyword, pageable);
        } else {
            attitudeSkillPage = attitudeSkillRepo.findAll(pageable);
        }
        List<AttitudeSkillReqDto> attitudeSkillReqDto = new ArrayList<>();
        for (AttitudeSkill attitudeSkill : attitudeSkillPage.getContent()) {
            attitudeSkillReqDto.add(AttitudeSkillReqDto.fromEntity(attitudeSkill));
        }
        Log.info("End getPaginatedAttitudeSkill in AttitudeSkillServImpl");
        return attitudeSkillReqDto;
    }

    @Override
    public AttitudeSkillReqDto getAttitudeSkillById(UUID id) {
        Log.info("Start getAttitudeSkillById in AttitudeSkillServImpl");
        AttitudeSkill attitudeSkill = attitudeSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("AttitudeSkill not found"));
        Log.info("End getAttitudeSkillById in AttitudeSkillServImpl");
        return AttitudeSkillReqDto.fromEntity(attitudeSkill);
    }

    @Override
    public AttitudeSkillReqDto createAttitudeSkill(AttitudeSkillDto attitudeSkillDto) {
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        Optional<GroupAttitudeSkill> user = groupAttitudeSkillRepo.findById(attitudeSkillDto.getGroupId());
        if (user.isPresent()) {
            AttitudeSkill attitudeSkill = attitudeSkillDto.toEntity(attitudeSkillDto, user.get(), null, null, currentUser, new java.util.Date());
            attitudeSkillRepo.save(attitudeSkill);
            return AttitudeSkillReqDto.fromEntity(attitudeSkillRepo.save(attitudeSkill));
        }else {
            throw new RuntimeException("Attitude Skill not found");
        }
    }

    @Override
    public AttitudeSkillReqDto updateAttitudeSkill(UUID id, AttitudeSkillDto attitudeSkillDto) {
        Log.info("Start updateAttitudeSkill in AttitudeSkillServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        AttitudeSkill attitudeSkill = attitudeSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AttitudeSkill not found"));
        attitudeSkill.setAttitudeSkill(attitudeSkillDto.getAttitudeSkill());     
        attitudeSkill.setGroupAttitudeSkill(groupAttitudeSkillRepo.findById(attitudeSkillDto.getGroupId()).orElseThrow(() -> new RuntimeException("Group AttitudeSkill not found")));
        attitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        attitudeSkill.setUpdatedBy(currentUser);
        attitudeSkill.setUpdatedAt(new java.util.Date());
        attitudeSkillRepo.save(attitudeSkill);
        Log.info("End updateAttitudeSkill in AttitudeSkillServImpl");
        return AttitudeSkillReqDto.fromEntity(attitudeSkillRepo.save(attitudeSkill));
    }

    @Override
    public Boolean deleteAttitudeSkill(UUID id) {
        Log.info("Start deleteAttitudeSkill in AttitudeSkillServImpl");
        if (attitudeSkillRepo.existsById(id)) {
            attitudeSkillRepo.deleteById(id);  
            Log.info("End deleteAttitudeSkill in AttitudeSkillServImpl");
            return true;
        }
        throw new RuntimeException("AttitudeSkill not found");
    }

    @Override
    public List<AttitudeSkillReqDto> getAllAttitudeSkillEnabled() {
        Log.info("Start getAllAttitudeSkillEnabled in AttitudeSkillServImpl");
        List<AttitudeSkill> response = attitudeSkillRepo.findByEnabled(1);
        List<AttitudeSkillReqDto> attitudeSkillReqDto = new ArrayList<>();
        for (AttitudeSkill attitudeSkill : response) {
            attitudeSkillReqDto.add(AttitudeSkillReqDto.fromEntity(attitudeSkill));
        }
        Log.info("End getAllAttitudeSkillEnabled in AttitudeSkillServImpl");
        return attitudeSkillReqDto;
    }

    @Override
    public long count() {
        return attitudeSkillRepo.count(); 
    }

    @Override
    public long countBySearchKeyword(String searchKeyword) {
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            return attitudeSkillRepo.countByAttitudeSkillContainingIgnoreCase(searchKeyword);
        }
        return attitudeSkillRepo.count(); 
    }
}