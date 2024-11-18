package com.example.LokaKarya.Controller;

import com.example.LokaKarya.Dto.ManagerDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.LokaKarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;
import com.example.LokaKarya.Services.GroupAttitudeSkillServ;
import com.example.LokaKarya.util.ServerResponseList;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group-attitude-skill")
public class GroupAttitudeSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(GroupAttitudeSkillController.class);

    @Autowired
    GroupAttitudeSkillServ groupAttitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<GroupAttitudeSkillDto>>> getAllGroupAttitudeSkill() {
        Log.info("Start getAllGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<List<GroupAttitudeSkillDto>> response = new ManagerDto<>();
        List<GroupAttitudeSkillDto> content = groupAttitudeSkillServ.getAllGroupAttitudeSkill();

        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllGroupAttitudeSkill in GroupAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<GroupAttitudeSkillDto>> saveGroupAttitudeSkill(@RequestBody GroupAttitudeSkillReqDto groupAttitudeSkillDto) {
        Log.info("Start saveGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
        GroupAttitudeSkillDto content = groupAttitudeSkillServ.createGroupAttitudeSkill(groupAttitudeSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveGroupAttitudeSkill in GroupAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ManagerDto<GroupAttitudeSkillDto>> getGroupAttitudeSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getGroupAttitudeSkillDetail in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
        GroupAttitudeSkillDto content = groupAttitudeSkillServ.getGroupAttitudeSkillById(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getGroupAttitudeSkillDetail in GroupAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<GroupAttitudeSkillDto>> updateGroupAttitudeSkill(@PathVariable("id") UUID id, @RequestBody GroupAttitudeSkillReqDto groupAttitudeSkillDto) {
        Log.info("Start updateGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<GroupAttitudeSkillDto> response = new ManagerDto<>();
        GroupAttitudeSkillDto content = groupAttitudeSkillServ.updateGroupAttitudeSkill(id, groupAttitudeSkillDto);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateGroupAttitudeSkill in GroupAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ManagerDto<Boolean>> deleteGroupAttitudeSkill(@PathVariable("id") UUID id) {
        Log.info("Start deleteGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();

        ManagerDto<Boolean> response = new ManagerDto<>();
        Boolean content = groupAttitudeSkillServ.deleteGroupAttitudeSkill(id);

        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success delete data", executionTime));
        Log.info("End deleteGroupAttitudeSkill in GroupAttitudeSkillController");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
