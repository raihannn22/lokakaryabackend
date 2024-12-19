package com.example.lokakarya.Dto.AssessmentSummary;


import com.example.lokakarya.Entity.AssessmentSummary;
import com.example.lokakarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AssessmentSummaryDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("SCORE")
    private Integer score;
    @JsonProperty("STATUS")
    private Integer status;

    public static AssessmentSummary toEntity(AssessmentSummaryDto assessmentSummaryDto, User user, UUID updatedBy
    , Date updatedAt, UUID createdBy, Date CreatedAt) {
        AssessmentSummary assessmentSummary = new AssessmentSummary();
        assessmentSummary.setId(assessmentSummaryDto.getId());
        assessmentSummary.setUser(user);
        assessmentSummary.setYear(assessmentSummaryDto.getYear());
        assessmentSummary.setScore(assessmentSummaryDto.getScore());
        assessmentSummary.setStatus(assessmentSummaryDto.getStatus());
        assessmentSummary.setUpdatedBy(updatedBy);
        assessmentSummary.setUpdatedAt(updatedAt);
        assessmentSummary.setCreatedBy(createdBy);
        assessmentSummary.setCreatedAt(CreatedAt);
        return assessmentSummary;
    }
}

