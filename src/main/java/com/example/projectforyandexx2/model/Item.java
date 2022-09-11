package com.example.projectforyandexx2.model;

import com.example.projectforyandexx2.dto.request.FileRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class Item {
    private String updateDate;
    private List<FileRequestDto> items;
}
