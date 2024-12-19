package com.example.lokakarya.Dto.AssessmentSummary;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TotalScoreDto  {
    private UUID userId;
    private String userName;
    private Double totalScore;

    // Constructor
//    public void UserScoreResponse(Long userId, String userName, Double totalScore) {
//        this.userId = userId;
//        this.userName = userName;
//        this.totalScore = totalScore;
//    }

    // Getters and Setters
}