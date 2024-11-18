package com.example.LokaKarya.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Schema
public class ResponseDetail {
    @JsonProperty("status")
    @Schema(example = "200", description = "status code")
    private int status;
    @JsonProperty("message")
    @Schema(example = "Success", description = "response message")
    private String message;
    @JsonProperty("detailMessage")
    @Schema(example = "Success get data", description = "detailed response message")
    private Object detailMessage;
    @JsonProperty("executionTime")
    @Schema(example = "Execution time", description = "time execution per request")
    private String executionTime;
    @JsonProperty("detailInfo")
    @Schema(example = "OK", description = "detail info")
    private Object detailInfo;
}
