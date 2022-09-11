package com.example.projectforyandexx2.service.externalService;

import com.example.projectforyandexx2.dto.response.FileValidationResponseDto;
import com.example.projectforyandexx2.model.FileValidation;
import com.example.projectforyandexx2.service.internalService.FileValidationInternalService;
import com.example.projectforyandexx2.utils.DateMapper;
import com.example.projectforyandexx2.utils.FileValidationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileValidationExternalService {
    @Autowired
    private FileValidationInternalService fileValidationInternalService;
    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private FileValidationMapper fileValidationMapper;

    public List<FileValidationResponseDto> getHistory(String id) {
        return fileValidationMapper.toListDto(fileValidationInternalService.getFileHistory(id));
    }

    public List<FileValidationResponseDto> getHistory(String id, String dateStart, String dateEnd) {
        return fileValidationMapper.toListDto(fileValidationInternalService.getFileHistory(id, dateMapper.stringDateToLong(dateStart), dateMapper.stringDateToLong(dateEnd)));
    }

    public void deleteItemHistory(List<String> ids) {
        fileValidationInternalService.deleteItemHistory(ids);
    }

    public void saveAll(List<FileValidation> valid) {
        fileValidationInternalService.saveAll(valid);
    }
}
