package com.example.projectforyandexx2.service.externalService;

import com.example.projectforyandexx2.dto.response.FileValidationResponseDto;
import com.example.projectforyandexx2.service.internalService.FileValidationInternalService;
import com.example.projectforyandexx2.utils.DateMapper;
import com.example.projectforyandexx2.utils.FileValidationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileValidationExternalService {
    @Autowired
    private FileValidationInternalService service;
    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private FileValidationMapper mapper;

    public List<FileValidationResponseDto> getHistory(String id) {
        return service.getFileHistory(id).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<FileValidationResponseDto> getHistory(String id, String dateStart, String dateEnd) {
        return service.getFileHistory(id, dateMapper.toMilli(dateStart), dateMapper.toMilli(dateEnd))
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public void deleteItemHistory(List<String> ids) {
        service.deleteItemHistory(ids);
    }
}
