package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.AssessmentSummary.TotalScoreDto;
import com.example.LokaKarya.Entity.*;
import com.example.LokaKarya.Repository.*;
import com.example.LokaKarya.util.GetUserUtil;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.LokaKarya.Services.AssessmentSummaryServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssessmentSummaryServImpl implements AssessmentSummaryServ {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryServImpl.class);

    @Autowired
    AssessmentSummaryRepo assessmentSummaryRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    EmpAchievementSkillRepo achievementRepo;

    @Autowired
    EmpAttitudeSkillRepo attitudeSkillRepo;

    @Autowired
    GetUserUtil getUserUtil;

    @Override
    public List<AssessmentSummaryReqDto> getAllAssessmentSummary() {
        Log.info("Start getAllAssessmentSummary in AssessmentSummaryServImpl");
        List<AssessmentSummary> response = assessmentSummaryRepo.findAll();
        List<AssessmentSummaryReqDto> assessmentSummaryReqDto = new ArrayList<>();
        for (AssessmentSummary assessmentSummary : response) {
            assessmentSummaryReqDto.add(AssessmentSummaryReqDto.fromEntity(assessmentSummary));
        }
        Log.info("End getAllAssessmentSummary in AssessmentSummaryServImpl");
        return assessmentSummaryReqDto;
    }

    @Override
    public AssessmentSummaryReqDto getAssessmentSummaryById(UUID id) {
        Log.info("Start getAssessmentSummaryById in AssessmentSummaryServImpl");
        AssessmentSummary assessmentSummary = assessmentSummaryRepo.findById(id).orElseThrow(() -> new RuntimeException("AssessmentSummary not found"));
        Log.info("End getAssessmentSummaryById in AssessmentSummaryServImpl");
        return AssessmentSummaryReqDto.fromEntity(assessmentSummary);
    }

    @Override
    public AssessmentSummaryReqDto createAssessmentSummary(AssessmentSummaryDto assessmentSummaryDto) {
        Log.info("Start createAssessmentSummary in AssessmentSummaryServImpl");
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        Optional<User> user = userRepo.findById(assessmentSummaryDto.getUserId());
        if (user.isPresent()) {
            AssessmentSummary assessmentSummary = assessmentSummaryDto.toEntity(assessmentSummaryDto, user.get(), null, null, currentUserEntity, new java.util.Date());
            assessmentSummaryRepo.save(assessmentSummary);
            Log.info("End createAssessmentSummary in AssessmentSummaryServImpl");
            return AssessmentSummaryReqDto.fromEntity(assessmentSummaryRepo.save(assessmentSummary));
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public AssessmentSummaryReqDto updateAssessmentSummary(UUID id, AssessmentSummaryDto assessmentSummaryDto) {
        Log.info("Start updateAssessmentSummary in AssessmentSummaryServImpl");
        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        AssessmentSummary assessmentSummary = assessmentSummaryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessmentSummary not found"));
        AssessmentSummary assessmentSummary1 = assessmentSummaryDto.toEntity(assessmentSummaryDto, assessmentSummary.getUser(), currentUserEntity, new java.util.Date(), assessmentSummary.getCreatedBy(), assessmentSummary.getCreatedAt());
        Log.info("End updateAssessmentSummary in AssessmentSummaryServImpl");
        return AssessmentSummaryReqDto.fromEntity(assessmentSummaryRepo.save(assessmentSummary1));
    }

    @Override
    public Boolean deleteAssessmentSummary(UUID id) {
        Log.info("Start deleteAssessmentSummary in AssessmentSummaryServImpl");
        if (assessmentSummaryRepo.existsById(id)) {
            assessmentSummaryRepo.deleteById(id);
            Log.info("End deleteAssessmentSummary in AssessmentSummaryServImpl");
            return true;
        }
        throw new RuntimeException("AssessmentSummary not found");
    }

    @Override
    public List<TotalScoreDto> calculateTotalScoresForAllUsers(int year) {
        Log.info("Start calculateTotalScoresForAllUsers in AssessmentSummaryServImpl");
        List<User> users = userRepo.findAll();

        List<TotalScoreDto> userScores = new ArrayList<>();
        for (User user : users) {
            // Ambil data attitude skill dan achievement untuk user ini
            List<EmpAttitudeSkill> attitudeSkills = attitudeSkillRepo.findByUserIdAndAssessmentYear(user.getId(), year);
            List<EmpAchievementSkill> achievements = achievementRepo.findByUserIdAndAssessmentYear(user.getId(),year);

            // Hitung total score untuk user ini
            double totalScore = calculateUserTotalScore(attitudeSkills, achievements);

            // Tambahkan hasil ke response
            userScores.add(new TotalScoreDto(user.getId(), user.getUsername(), totalScore));
        }
        Log.info("End calculateTotalScoresForAllUsers in AssessmentSummaryServImpl");
        return userScores;
    }

    private double calculateUserTotalScore(List<EmpAttitudeSkill> attitudeSkills, List<EmpAchievementSkill> achievements) {
        Log.info("Start calculateUserTotalScore in AssessmentSummaryServImpl");
        double score = 0.0;

        // Hitung score dari attitude skills
        Map<GroupAttitudeSkill, List<EmpAttitudeSkill>> groupedAttitudes = attitudeSkills.stream()
                .collect(Collectors.groupingBy(skill -> skill.getAttitudeSkill().getGroupAttitudeSkill()));

        for (Map.Entry<GroupAttitudeSkill, List<EmpAttitudeSkill>> entry : groupedAttitudes.entrySet()) {
            GroupAttitudeSkill group = entry.getKey();
            List<EmpAttitudeSkill> skillsInGroup = entry.getValue();

            // Hitung rata-rata score dalam grup
            double groupAverageScore = skillsInGroup.stream()
                    .mapToDouble(EmpAttitudeSkill::getScore)
                    .average()
                    .orElse(0.0);

            // Kalikan rata-rata score dengan persen grupnya
            score += groupAverageScore * (group.getPercentage() / 100.0);
        }

        // Hitung score dari achievements
        Map<GroupAchievement, List<EmpAchievementSkill>> groupedAchievements = achievements.stream()
                .collect(Collectors.groupingBy(achievement -> achievement.getAchievement().getGroupAchievement()));

        for (Map.Entry<GroupAchievement, List<EmpAchievementSkill>> entry : groupedAchievements.entrySet()) {
            GroupAchievement group = entry.getKey();
            List<EmpAchievementSkill> achievementsInGroup = entry.getValue();

            // Hitung rata-rata score dalam grup
            double groupAverageScore = achievementsInGroup.stream()
                    .mapToDouble(EmpAchievementSkill::getScore)
                    .average()
                    .orElse(0.0);

            // Kalikan rata-rata score dengan persen grupnya
            score += groupAverageScore * (group.getPercentage() / 100.0);
        }
        Log.info("End calculateUserTotalScore in AssessmentSummaryServImpl");
        return score;
    }
}