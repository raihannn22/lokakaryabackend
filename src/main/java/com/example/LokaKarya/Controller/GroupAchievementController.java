package com.example.LokaKarya.Controller;

import com.example.LokaKarya.Dto.GroupAchievementDto;
import com.example.LokaKarya.Service.GroupAchievementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/group-achievements")
public class GroupAchievementController {

    @Autowired
    private GroupAchievementService groupAchievementService;

    @GetMapping
    public ResponseEntity<List<GroupAchievementDto>> getAllGroupAchievements() {
        List<GroupAchievementDto> achievements = groupAchievementService.getAllGroupAchievements();
        return ResponseEntity.ok(achievements);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GroupAchievementDto> getGroupAchievementById(@PathVariable UUID id) {
        GroupAchievementDto achievement = groupAchievementService.getGroupAchievementById(id);
        return achievement != null ? ResponseEntity.ok(achievement) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<GroupAchievementDto> createGroupAchievement(@RequestBody GroupAchievementDto groupAchievementDto) {
        GroupAchievementDto createdAchievement = groupAchievementService.createGroupAchievement(groupAchievementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAchievement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupAchievementDto> updateGroupAchievement(@PathVariable UUID id, @RequestBody GroupAchievementDto groupAchievementDto) {
        GroupAchievementDto updatedAchievement = groupAchievementService.updateGroupAchievement(id, groupAchievementDto);
        return updatedAchievement != null ? ResponseEntity.ok(updatedAchievement) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupAchievement(@PathVariable UUID id) {
        groupAchievementService.deleteGroupAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
