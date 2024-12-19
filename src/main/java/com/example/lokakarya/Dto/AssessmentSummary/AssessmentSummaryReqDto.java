package com.example.lokakarya.Dto.AssessmentSummary;


import com.example.lokakarya.Entity.AssessmentSummary;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AssessmentSummaryReqDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("status")
    private Integer status = 1;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;
    @JsonProperty("updated_at")
    private Date updatedAt;

    public static AssessmentSummaryReqDto fromEntity(AssessmentSummary assessmentSummary) {
        AssessmentSummaryReqDto assessmentSummaryReqDto = new AssessmentSummaryReqDto();
        assessmentSummaryReqDto.setId(assessmentSummary.getId());
        assessmentSummaryReqDto.setUserId(assessmentSummary.getUser().getId());
        assessmentSummaryReqDto.setYear(assessmentSummary.getYear());
        assessmentSummaryReqDto.setScore(assessmentSummary.getScore());
        assessmentSummaryReqDto.setStatus(assessmentSummary.getStatus());
        assessmentSummaryReqDto.setCreatedBy(assessmentSummary.getCreatedBy());
        assessmentSummaryReqDto.setCreatedAt(assessmentSummary.getCreatedAt());
        assessmentSummaryReqDto.setUpdatedBy(assessmentSummary.getUpdatedBy());
        assessmentSummaryReqDto.setUpdatedAt(assessmentSummary.getUpdatedAt());
        return assessmentSummaryReqDto;
    }
}
