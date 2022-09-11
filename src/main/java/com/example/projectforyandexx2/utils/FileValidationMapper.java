package com.example.projectforyandexx2.utils;

import com.example.projectforyandexx2.dto.response.FileValidationResponseDto;
import com.example.projectforyandexx2.model.FileValidation;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class FileValidationMapper {
    @Autowired
    private DateMapper dateMapper;

    public FileValidationResponseDto toDto(FileValidation fileValidation) {
        return FileValidationResponseDto.builder()
                .id(fileValidation.getUuid())
                .parentId(fileValidation.getParentId())
                .size(fileValidation.getSize())
                .type(fileValidation.getType())
                .url(fileValidation.getUrl())
                .date(dateMapper.toString(fileValidation.getUpdateDate())).build();
    }
}
