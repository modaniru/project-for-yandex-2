package com.example.projectforyandexx2.utils;

import com.example.projectforyandexx2.dto.request.FileRequestDto;
import com.example.projectforyandexx2.dto.response.FileResponseDto;
import com.example.projectforyandexx2.model.File;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = DateMapper.class)
public abstract class FileMapper {
    @Mapping(source = "date", target = "date", qualifiedByName = "longToStringDate")
    public abstract FileResponseDto toDto(File model);

    @Mapping(source = "date", target = "date", qualifiedByName = "stringDateToLong")
    public abstract File toModel(FileRequestDto dto);

    public abstract List<FileResponseDto> listToDto(List<File> files);

    public abstract List<File> listToModel(List<FileRequestDto> dtos);
}
