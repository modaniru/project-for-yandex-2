package com.example.projectforyandexx2.dto.request;

import lombok.Data;

@Data
public class FileRequestDto {
    private String id;
    private String type;
    private String url;
    private String parentId;
    private int size;
    private String date;
}
