package com.example.projectforyandexx2.utils;

import com.example.projectforyandexx2.dto.request.FileRequestDto;
import com.example.projectforyandexx2.dto.response.FileResponseDto;
import com.example.projectforyandexx2.model.File;
import com.example.projectforyandexx2.service.internalService.FileInternalService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class FileMapper {
    @Autowired
    FileInternalService service;

    @Autowired
    DateMapper dateMapper;

    public FileResponseDto toDto(File model) {
        FileResponseDto file = FileResponseDto.builder()
                .id(model.getId())
                .type(model.getType())
                .url(model.getUrl())
                .parentId(model.getParentId())
                .size(model.getSize())
                .date(dateMapper.toString(model.getDate())).build();
        List<FileResponseDto> child = service.getAllChild(model.getId()).stream().map(this::toDto).collect(Collectors.toList());
        if (!child.isEmpty()) file.setChildren(child);
        return file;
    }

    public File toModel(FileRequestDto dto) {
        return File.builder()
                .id(dto.getId())
                .type(dto.getType())
                .url(dto.getUrl())
                .parentId(dto.getParentId())
                .size(dto.getSize())
                .date(dateMapper.toMilli(dto.getDate())).build();
    }
}
