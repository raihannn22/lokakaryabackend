//package com.example.LokaKarya.Dto;
//
//
//import com.example.LokaKarya.Entity.AssessmentSummary;
//import com.example.LokaKarya.Entity.User;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.*;
//import org.springframework.beans.BeanUtils;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.UUID;
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@ToString
//public class AssessmentSummaryDto {
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private UUID id;
//
//    @JsonProperty("USER_ID")
//    private UUID userId;
//
//    @JsonProperty("YEAR")
//    private Integer year;
//
//    @JsonProperty("SCORE")
//    private Float score;
//
//    @JsonProperty("STATUS")
//    private Integer status = 1;
//
////    @JsonProperty("CREATED_BY")
////    private String createdBy;
////
////    @JsonProperty("CREATED_AT")
////    private Date createdAt;
////
////    @JsonProperty("UPDATED_BY")
////    private String updatedBy;
////
////    @JsonProperty("UPDATED_AT")
////    private Date updatedAt;
//
//    public static AssessmentSummary convertToEntity(AssessmentSummaryDto dto) {
//        AssessmentSummary result = new AssessmentSummary();
//        BeanUtils.copyProperties(dto, result);
//        System.out.println("Dto to Entity" + result);
//        return result;
//    };
//
//    public static AssessmentSummary toEntity(
//            AssessmentSummaryDto assessSumReqDto, User user,
//            UUID createdBy, LocalDate createdAt, UUID updatedBy, LocalDate updatedAt)
//    {
//        AssessmentSummary assessSum = new AssessmentSummary();
//        user.setId(assessSumReqDto.getUserId());
//        assessSum.setId(assessSumReqDto.getId());
//        assessSum.setUser(user);
//        assessSum.setYear(assessSumReqDto.getYear());
//        assessSum.setScore(assessSumReqDto.getScore());
//        assessSum.setStatus(assessSumReqDto.getStatus());
//        assessSum.setCreatedAt(Date.valueOf(createdAt));
//        assessSum.setCreatedBy(createdBy);
//        assessSum.setUpdatedAt(Date.valueOf(updatedAt));
//        assessSum.setUpdatedBy(updatedBy);
//        return assessSum;
//    }
//
//
//}
