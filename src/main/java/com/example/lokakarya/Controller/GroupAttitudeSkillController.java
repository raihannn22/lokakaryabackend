package com.example.lokakarya.Controller;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.lokakarya.Dto.ManagerDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillReqDto;
import com.example.lokakarya.Dto.GroupAttitudeSkill.GroupAttitudeSkillWithDetailsDto;
import com.example.lokakarya.Services.GroupAttitudeSkillServ;
import com.example.lokakarya.util.ServerResponseList;

@RestController
@RequestMapping("/group-attitude-skill")
public class GroupAttitudeSkillController extends ServerResponseList {
    private final Logger Log = LoggerFactory.getLogger(GroupAttitudeSkillController.class);

    @Autowired
    GroupAttitudeSkillServ groupAttitudeSkillServ;

    @GetMapping("/all")
    public ResponseEntity<ManagerDto<List<GroupAttitudeSkillReqDto>>> getAllGroupAttitudeSkill() {
        Log.info("Start getAllGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<GroupAttitudeSkillReqDto>> response = new ManagerDto<>();
        List<GroupAttitudeSkillReqDto> content = groupAttitudeSkillServ.getAllGroupAttitudeSkill();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllGroupAttitudeSkill in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<ManagerDto<List<GroupAttitudeSkillReqDto>>> getPaginatedGroupAttitudeSkill(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "groupName") String sort, 
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String searchKeyword) { 
        Log.info("Start getPaginatedGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();
        long totalRecords = groupAttitudeSkillServ.count();
        ManagerDto<List<GroupAttitudeSkillReqDto>> response = new ManagerDto<>();
        List<GroupAttitudeSkillReqDto> content = groupAttitudeSkillServ.getPaginatedGroupAttitudeSkill(page, size, sort, direction, searchKeyword);
        response.setContent(content);
        response.setTotalData(totalRecords);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getPaginatedGroupAttitudeSkill in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/with-details")
    public ResponseEntity<ManagerDto<List<GroupAttitudeSkillWithDetailsDto>>> getAllGroupAttitudeSkillsWithDetails() {
        Log.info("Start getAllGroupAttitudeSkillsWithDetails in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<GroupAttitudeSkillWithDetailsDto>> response = new ManagerDto<>();
        List<GroupAttitudeSkillWithDetailsDto> content = groupAttitudeSkillServ.getAllGroupAttitudeSkillsWithDetails();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllGroupAttitudeSkillsWithDetails in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<ManagerDto<GroupAttitudeSkillReqDto>> saveGroupAttitudeSkill(@RequestBody GroupAttitudeSkillDto groupAttitudeSkillDto) {
        Log.info("Start saveGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<GroupAttitudeSkillReqDto> response = new ManagerDto<>();
        GroupAttitudeSkillReqDto content = groupAttitudeSkillServ.createGroupAttitudeSkill(groupAttitudeSkillDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success save data", executionTime));
        Log.info("End saveGroupAttitudeSkill in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManagerDto<GroupAttitudeSkillReqDto>> getGroupAttitudeSkillDetail(@PathVariable("id") UUID id) {
        Log.info("Start getGroupAttitudeSkillDetail in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<GroupAttitudeSkillReqDto> response = new ManagerDto<>();
        GroupAttitudeSkillReqDto content = groupAttitudeSkillServ.getGroupAttitudeSkillById(id);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getGroupAttitudeSkillDetail in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ManagerDto<GroupAttitudeSkillReqDto>> updateGroupAttitudeSkill(@PathVariable("id") UUID id, @RequestBody GroupAttitudeSkillDto groupAttitudeSkillDto) {
        Log.info("Start updateGroupAttitudeSkill in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<GroupAttitudeSkillReqDto> response = new ManagerDto<>();
        GroupAttitudeSkillReqDto content = groupAttitudeSkillServ.updateGroupAttitudeSkill(id, groupAttitudeSkillDto);
        response.setContent(content);
        response.setTotalRows(1);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success update data", executionTime));
        Log.info("End updateGroupAttitudeSkill in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
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
        Log.info("End deleteGroupAttitudeSkill in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/all/enabled")
    public ResponseEntity<ManagerDto<List<GroupAttitudeSkillReqDto>>>  getAllGroupAttitudeSkillEnabled() {
        Log.info("Start getAllGroupAttitudeSkillEnabled in GroupAttitudeSkillController");
        long startTime = System.currentTimeMillis();
        ManagerDto<List<GroupAttitudeSkillReqDto>> response = new ManagerDto<>();
        List<GroupAttitudeSkillReqDto> content = groupAttitudeSkillServ.getAllGroupAttitudeSkillEnabled();
        response.setContent(content);
        response.setTotalRows(content.size());
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        response.setInfo(getInfoOk("Success get data", executionTime));
        Log.info("End getAllGroupAttitudeSkillEnabled in GroupAttitudeSkillController, time: " + (endTime - startTime) + "ms");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}