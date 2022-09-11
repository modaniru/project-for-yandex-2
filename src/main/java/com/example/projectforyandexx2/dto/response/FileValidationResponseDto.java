package com.example.projectforyandexx2.dto.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FileValidationResponseDto {
    private String id;
    private String url;
    private String parentId;
    private String type;
    private Integer size;
    private String date;
}
