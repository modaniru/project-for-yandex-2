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
    private FileInternalService fileInternalService;
    @Autowired
    private DateMapper dateMapper;
    @Autowired
    private FileMapper fileMapper;

    public void saveItemList(Item item) {
        List<FileRequestDto> dtos = item.getItems().stream().peek(file -> {
            file.setDate(item.getUpdateDate());
        }).collect(Collectors.toList());
        fileInternalService.saveFileList(fileMapper.listToModel(dtos));
    }

    public FileResponseDto getFile(String id) {
        FileResponseDto file = fileMapper.toDto(fileInternalService.getFile(id));
        List<FileResponseDto> children = fileInternalService.getAllChild(id).stream().map(f -> getFile(f.getId())).collect(Collectors.toList());
        if (children.isEmpty()) return file;
        file.setChildren(children);
        return file;
    }

    public void delete(String id) {
        fileInternalService.delete(id);
    }

    public List<FileResponseDto> getUpdates(String stringDate) {
        Long date = dateMapper.stringDateToLong(stringDate);
        Long dateMinusDay = dateMapper.toMilliMinusDay(stringDate);
        return fileMapper.listToDto(fileInternalService.getUpdates(dateMinusDay, date));
    }
}
