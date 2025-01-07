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
}