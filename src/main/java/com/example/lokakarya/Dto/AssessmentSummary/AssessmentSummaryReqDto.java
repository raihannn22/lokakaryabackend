package com.example.lokakarya.Dto.AssessmentSummary;
import com.example.lokakarya.Entity.AppRole;
import com.example.lokakarya.Entity.AppUserRole;
import com.example.lokakarya.Entity.AssessmentSummary;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AssessmentSummaryReqDto {
    @JsonProperty("user_id")
    private UUID userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("full_name")
    private String fullName;
    
    @JsonProperty("position")
    private String position;
    
    @JsonProperty("email_address")
    private String emailAddress;
    
    @JsonProperty("employee_status")
    private Integer employeeStatus;
    
    @JsonProperty("join_date")
    private Date joinDate;
    
    @JsonProperty("enabled")
    private Integer enabled;
    
    @JsonProperty("division_name")
    private String divisionName;
    
    @JsonProperty("app_role")
    private List<AppRole> appRole;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("score")
    private Double score;

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
        assessmentSummaryReqDto.setId(assessmentSummary.getUser().getId());
        assessmentSummaryReqDto.setUsername(assessmentSummary.getUser().getUsername());
        assessmentSummaryReqDto.setFullName(assessmentSummary.getUser().getFullName());
        assessmentSummaryReqDto.setPosition(assessmentSummary.getUser().getPosition());
        assessmentSummaryReqDto.setEmailAddress(assessmentSummary.getUser().getEmail());
        assessmentSummaryReqDto.setEmployeeStatus(assessmentSummary.getUser().getEmployeeStatus());
        assessmentSummaryReqDto.setJoinDate(assessmentSummary.getUser().getJoinDate());
        assessmentSummaryReqDto.setEnabled(assessmentSummary.getUser().getEnabled());
        assessmentSummaryReqDto.setDivisionName(assessmentSummary.getUser().getDivision().getDivisionName());
        if (assessmentSummary.getUser().getAppRoles() != null) {
            assessmentSummaryReqDto.setAppRole(assessmentSummary.getUser().getAppRoles().stream().map(AppUserRole::getAppRole).toList());
        }else{
            assessmentSummaryReqDto.setAppRole(null);
        }
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