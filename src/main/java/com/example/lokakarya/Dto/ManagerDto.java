package com.example.lokakarya.Dto;
import com.example.lokakarya.util.ResponseDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ManagerDto<T> {
    @JsonProperty("info")
    private ResponseDetail info;

    @JsonProperty("total_rows")
    private int totalRows;

    @JsonProperty("total_data")
    private long totalData;

    @JsonProperty("content")
    private T content;
}