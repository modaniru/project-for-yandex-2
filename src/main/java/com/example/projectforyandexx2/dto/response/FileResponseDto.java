package com.example.projectforyandexx2.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FileResponseDto {
    private String id;
    private String type;
    private String url;
    private String parentId;
    private int size;
    private String date;
    private List<FileResponseDto> children;
}
