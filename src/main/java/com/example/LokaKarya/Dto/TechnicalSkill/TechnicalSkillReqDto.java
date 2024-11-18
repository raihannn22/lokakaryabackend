package com.example.LokaKarya.Dto.TechnicalSkill;

import com.example.LokaKarya.Dto.TechnicalSkill.TechnicalSkillReqDto;
import com.example.LokaKarya.Entity.TechnicalSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TechnicalSkillReqDto {
    @JsonProperty("technical_skill")
    private String technicalSkill;
    @JsonProperty("enabled")
    private Integer enabled;

    public static TechnicalSkill toEntity(TechnicalSkillReqDto technicalSkillDto) {
        TechnicalSkill technicalSkill = new TechnicalSkill();
        technicalSkill.setTechnicalSkill(technicalSkillDto.getTechnicalSkill());
        technicalSkill.setEnabled(technicalSkillDto.getEnabled());
        return technicalSkill;
    }
}


