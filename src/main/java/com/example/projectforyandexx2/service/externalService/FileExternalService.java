package com.example.projectforyandexx2.service.externalService;

import com.example.projectforyandexx2.dto.request.FileRequestDto;
import com.example.projectforyandexx2.dto.response.FileResponseDto;
import com.example.projectforyandexx2.model.Item;
import com.example.projectforyandexx2.service.internalService.FileInternalService;
import com.example.projectforyandexx2.utils.DateMapper;
import com.example.projectforyandexx2.utils.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileExternalService {
    @Autowired
    FileInternalService service;

    @Autowired
    DateMapper dateMapper;
    @Autowired
    FileMapper mapper;

    public void saveItemList(Item item) {
        List<FileRequestDto> dtos = item.getItems().stream().peek(f -> {
            f.setDate(item.getUpdateDate());
        }).collect(Collectors.toList());
        service.saveFileList(dtos.stream().map(mapper::toModel).collect(Collectors.toList()));
    }

    public FileResponseDto getFile(String id) {
        return mapper.toDto(service.getFile(id));
    }

    public List<FileResponseDto> delete(String id) {
        return service.delete(id).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<FileResponseDto> getUpdates(String stringDate) {
        Long date = dateMapper.toMilli(stringDate);
        Long dateMinusDay = dateMapper.toMilliMinusDay(stringDate);
        return service.getUpdates(dateMinusDay, date).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
