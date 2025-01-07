package com.example.lokakarya.Dto.TechnicalSkill;
import com.example.lokakarya.Entity.TechnicalSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TechnicalSkillDto {
    @JsonProperty("id")
    private UUID id;
    
    @JsonProperty("technical_skill")
    private String technicalSkill;
    
    @JsonProperty("enabled")
    private Integer enabled;
    
    public static TechnicalSkill toEntity(TechnicalSkillDto technicalSkillDto, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        TechnicalSkill technicalSkill = new TechnicalSkill();
        technicalSkill.setId(technicalSkillDto.getId());
        technicalSkill.setTechnicalSkill(technicalSkillDto.getTechnicalSkill());
        technicalSkill.setEnabled(technicalSkillDto.getEnabled());
        technicalSkill.setCreatedAt(createdAt);
        technicalSkill.setCreatedBy(createdBy);
        technicalSkill.setUpdatedAt(updateAt);
        technicalSkill.setUpdatedBy(updateBy);
        return technicalSkill;
    }
}