package com.example.lokakarya.Services.Impl;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.lokakarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.lokakarya.Dto.AssessmentSummary.TotalScoreDto;
import com.example.lokakarya.Entity.*;
import com.example.lokakarya.Repository.*;
import com.example.lokakarya.Services.AssessmentSummaryServ;
import com.example.lokakarya.util.GetUserUtil;

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
    @Transactional
    public List<TotalScoreDto> calculateTotalScoresForAllUsers(int year) {
        Log.info("Start calculateTotalScoresForAllUsers in AssessmentSummaryServImpl");
        List<User> users = userRepo.findAll();

        List<TotalScoreDto> userScores = new ArrayList<>();
        for (User user : users) {
            List<EmpAttitudeSkill> attitudeSkills = attitudeSkillRepo.findByUserIdAndAssessmentYear(user.getId(), year);
            List<EmpAchievementSkill> achievements = achievementRepo.findByUserIdAndAssessmentYear(user.getId(), year);

            double totalScore = calculateUserTotalScore(attitudeSkills, achievements);

            // Tambahkan ke daftar hasil
            userScores.add(new TotalScoreDto(user.getId(), user.getUsername(), totalScore));
        }
        Log.info("End calculateTotalScoresForAllUsers in AssessmentSummaryServImpl");
        return userScores;
    }

//    private double calculateUserTotalScore(List<EmpAttitudeSkill> attitudeSkills, List<EmpAchievementSkill> achievements) {
//        Log.info("Start calculateUserTotalScore in AssessmentSummaryServImpl");
//        double score = 0.0;
//
//        // Filter attitudeSkills berdasarkan group_enabled dan enabled
//        Map<GroupAttitudeSkill, List<EmpAttitudeSkill>> groupedAttitudes = attitudeSkills.stream()
//                .filter(skill -> skill.getAttitudeSkill().getGroupAttitudeSkill().getEnabled() == 1) // Check group_enabled == 1
//                .filter(skill -> skill.getAttitudeSkill().getEnabled() == 1) // Check enabled == 1
//                .collect(Collectors.groupingBy(skill -> skill.getAttitudeSkill().getGroupAttitudeSkill()));
//
//        for (Map.Entry<GroupAttitudeSkill, List<EmpAttitudeSkill>> entry : groupedAttitudes.entrySet()) {
//            GroupAttitudeSkill group = entry.getKey();
//            List<EmpAttitudeSkill> skillsInGroup = entry.getValue();
//            double groupAverageScore = skillsInGroup.stream()
//                    .mapToDouble(EmpAttitudeSkill::getScore)
//                    .average()
//                    .orElse(0.0);
//            score += groupAverageScore * (group.getPercentage() / 100.0);
//        }
//
//        // Filter achievements berdasarkan group_enabled dan enabled
//        Map<GroupAchievement, List<EmpAchievementSkill>> groupedAchievements = achievements.stream()
//                .filter(achievement -> achievement.getAchievement().getGroupAchievement().getEnabled() == 1) // Check group_enabled == 1
//                .filter(achievement -> achievement.getAchievement().getEnabled() == 1) // Check enabled == 1
//                .collect(Collectors.groupingBy(achievement -> achievement.getAchievement().getGroupAchievement()));
//
//        for (Map.Entry<GroupAchievement, List<EmpAchievementSkill>> entry : groupedAchievements.entrySet()) {
//            GroupAchievement group = entry.getKey();
//            List<EmpAchievementSkill> achievementsInGroup = entry.getValue();
//            double groupAverageScore = achievementsInGroup.stream()
//                    .mapToDouble(EmpAchievementSkill::getScore)
//                    .average()
//                    .orElse(0.0);
//            score += groupAverageScore * (group.getPercentage() / 100.0);
//        }
//
//        Log.info("End calculateUserTotalScore in AssessmentSummaryServImpl");
//        return score;
//    }

    private double calculateUserTotalScore(List<EmpAttitudeSkill> attitudeSkills, List<EmpAchievementSkill> achievements) {
        Log.info("Start calculateUserTotalScore in AssessmentSummaryServImpl");
        double score = 0.0;



        // Filter attitudeSkills berdasarkan group_enabled dan enabled
        Map<GroupAttitudeSkill, List<EmpAttitudeSkill>> groupedAttitudes = attitudeSkills.stream()
                .filter(skill -> skill.getAttitudeSkill().getGroupAttitudeSkill().getEnabled() == 1) // Check group_enabled == 1
                .filter(skill -> skill.getAttitudeSkill().getEnabled() == 1) // Check enabled == 1
                .collect(Collectors.groupingBy(skill -> skill.getAttitudeSkill().getGroupAttitudeSkill()));

        for (Map.Entry<GroupAttitudeSkill, List<EmpAttitudeSkill>> entry : groupedAttitudes.entrySet()) {
            GroupAttitudeSkill group = entry.getKey();
            List<EmpAttitudeSkill> skillsInGroup = entry.getValue();
            double groupAverageScore = skillsInGroup.stream()
                    .mapToDouble(EmpAttitudeSkill::getScore)
                    .average()
                    .orElse(0.0);
            score += groupAverageScore * (group.getPercentage() / 100.0);
        }

        // Filter achievements berdasarkan group_enabled dan enabled
        Map<GroupAchievement, List<EmpAchievementSkill>> groupedAchievements = achievements.stream()
                .filter(achievement -> achievement.getAchievement().getGroupAchievement().getEnabled() == 1) // Check group_enabled == 1
                .filter(achievement -> achievement.getAchievement().getEnabled() == 1) // Check enabled == 1
                .collect(Collectors.groupingBy(achievement -> achievement.getAchievement().getGroupAchievement()));

        for (Map.Entry<GroupAchievement, List<EmpAchievementSkill>> entry : groupedAchievements.entrySet()) {
            GroupAchievement group = entry.getKey();
            List<EmpAchievementSkill> achievementsInGroup = entry.getValue();

            // Ambil total jumlah achievement di grup ini dari tabel `achievement`
            int totalAchievementsInGroup = (int) group.getAchievements().stream()
                    .filter(ach -> ach.getGroupAchievement().getEnabled() == 1) // Pastikan grup enabled
                    .count();

            // Hitung total skor dari employee achievement
            double totalGroupScore = achievementsInGroup.stream()
                    .mapToDouble(EmpAchievementSkill::getScore)
                    .sum();

            // Rata-rata dihitung berdasarkan jumlah total achievement di grup
            double groupAverageScore = totalAchievementsInGroup > 0
                    ? totalGroupScore / totalAchievementsInGroup
                    : 0.0;

            // Tambahkan skor ke total dengan memperhitungkan persen grup
            score += groupAverageScore * (group.getPercentage() / 100.0);
        }

        Log.info("End calculateUserTotalScore in AssessmentSummaryServImpl");
        return score;
    }


    private void saveAssessmentSummary(User user, int year, double totalScore) {
        Log.info("Start saveAssessmentSummary in AssessmentSummaryServImpl");

        UUID currentUserEntity = getUserUtil.getCurrentUser().getId();
        AssessmentSummary assessmentSummary = new AssessmentSummary();

        assessmentSummary.setUser(user);
        assessmentSummary.setYear(year);
        assessmentSummary.setScore(totalScore);
        assessmentSummary.setStatus(0);
        assessmentSummary.setCreatedBy(currentUserEntity);
        assessmentSummary.setCreatedAt(new java.util.Date());


        assessmentSummaryRepo.save(assessmentSummary);

        Log.info("End saveAssessmentSummary in AssessmentSummaryServImpl");
    }

    @Override
    @Transactional
    public void calculateAndSaveScoreForUser(UUID userId, int year) {
        Log.info("Start calculateAndSaveScoreForUser in AssessmentSummaryServImpl");

        // Ambil user berdasarkan userId
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with ID " + userId + " not found");
        }

        Optional<AssessmentSummary> assessmentSummaryOpt = assessmentSummaryRepo.findByUserIdAndYear(userId, year);
        assessmentSummaryOpt.ifPresent(assessmentSummary -> assessmentSummaryRepo.delete(assessmentSummary));
        User user = userOptional.get();

        // Ambil data attitudeSkills dan achievements berdasarkan userId dan year
        List<EmpAttitudeSkill> attitudeSkills = attitudeSkillRepo.findByUserIdAndAssessmentYear(userId, year);
        List<EmpAchievementSkill> achievements = achievementRepo.findByUserIdAndAssessmentYear(userId, year);

        // Hitung skor total
        double totalScore = calculateUserTotalScore(attitudeSkills, achievements);

        // Simpan hasil ke database
        saveAssessmentSummary(user, year, totalScore);

        Log.info("End calculateAndSaveScoreForUser in AssessmentSummaryServImpl");

        // Return hasilnya dalam bentuk DTO
        new TotalScoreDto(user.getId(), user.getUsername(), totalScore);
    }

    @Override
    public List<AssessmentSummaryReqDto> getAllAssessmentSummaryByYear(int year) {
        Log.info("Start getAllAssessmentSummaryByYear in AssessmentSummaryServImpl");
        List<AssessmentSummary> response = assessmentSummaryRepo.findByYear(year);
        List<AssessmentSummaryReqDto> assessmentSummaryReqDto = new ArrayList<>();
        for (AssessmentSummary assessmentSummary : response) {
            assessmentSummaryReqDto.add(AssessmentSummaryReqDto.fromEntity(assessmentSummary));
        }
        Log.info("End getAllAssessmentSummary in AssessmentSummaryServImpl");
        return assessmentSummaryReqDto;
    }

    @Override
    public AssessmentSummaryReqDto setAssessmentSummary1(UUID id, int year) {
        Log.info("Start setAssessmentSummary1 in AssessmentSummaryServImpl");
        AssessmentSummary assessmentSummary = assessmentSummaryRepo.findByUserIdAndYear(id, year).orElseThrow(() -> new RuntimeException("AssessmentSummary not found"));
        assessmentSummary.setStatus(1);
        assessmentSummaryRepo.save(assessmentSummary);
        Log.info("End setAssessmentSummary1 in AssessmentSummaryServImpl");
        return AssessmentSummaryReqDto.fromEntity(assessmentSummary);
    }

    @Override
    public AssessmentSummaryReqDto setAssessmentSummary0(UUID id, int year) {
        Log.info("Start setAssessmentSummary2 in AssessmentSummaryServImpl");
        AssessmentSummary assessmentSummary = assessmentSummaryRepo.findByUserIdAndYear(id, year).orElseThrow(() -> new RuntimeException("AssessmentSummary not found"));
        assessmentSummary.setStatus(0);
        assessmentSummaryRepo.save(assessmentSummary);
        Log.info("End setAssessmentSummary2 in AssessmentSummaryServImpl");
        return AssessmentSummaryReqDto.fromEntity(assessmentSummary);
    }


}