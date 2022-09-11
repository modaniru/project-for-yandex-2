package com.example.projectforyandexx2.utils;

import com.example.projectforyandexx2.dto.response.FileValidationResponseDto;
import com.example.projectforyandexx2.model.FileValidation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = DateMapper.class)
public abstract class FileValidationMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "longToStringDate")
    public FileValidationResponseDto toDto(FileValidation fileValidation) {
        return FileValidationResponseDto.builder()
                .id(fileValidation.getUuid())
                .parentId(fileValidation.getParentId())
                .size(fileValidation.getSize())
                .type(fileValidation.getType())
                .url(fileValidation.getUrl()).build();
    }

    public List<FileValidationResponseDto> toListDto(List<FileValidation> dtos) {
        return dtos.stream().map(this::toDto).collect(Collectors.toList());
    }
}
