package com.example.lokakarya.Dto.TechnicalSkill;
import com.example.lokakarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.lokakarya.Entity.TechnicalSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TechnicalSkillReqDto {
    @JsonProperty("id")
    private UUID id;
    
    @JsonProperty("technical_skill")
    private String technicalSkill;
    
    @JsonProperty("enabled")
    private Integer enabled;

    public static TechnicalSkillReqDto fromEntity(TechnicalSkill technicalSkill) {
        TechnicalSkillReqDto technicalSkillReqDto = new TechnicalSkillReqDto();
        technicalSkillReqDto.setId(technicalSkill.getId());
        technicalSkillReqDto.setTechnicalSkill(technicalSkill.getTechnicalSkill());
        technicalSkillReqDto.setEnabled(technicalSkill.getEnabled());
        return technicalSkillReqDto;
    }
}