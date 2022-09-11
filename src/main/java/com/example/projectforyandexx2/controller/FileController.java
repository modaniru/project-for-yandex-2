package com.example.projectforyandexx2.controller;

import com.example.projectforyandexx2.dto.response.FileResponseDto;
import com.example.projectforyandexx2.model.Item;
import com.example.projectforyandexx2.service.externalService.FileExternalService;
import com.example.projectforyandexx2.service.externalService.FileValidationExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class FileController {
    @Autowired
    FileExternalService service;
    @Autowired
    FileValidationExternalService validService;

    @PostMapping("/imports")
    public void save(@RequestBody Item item) {
        service.saveItemList(item);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        List<FileResponseDto> files = service.delete(id);
        validService.deleteItemHistory(files.stream().map(FileResponseDto::getId).collect(Collectors.toList()));
    }

    @GetMapping("/nodes/{id}")
    public FileResponseDto getFile(@PathVariable("id") String id) {
        return service.getFile(id);
    }

    @GetMapping("/updates")
    public List<FileResponseDto> getUpdates(@RequestParam("date") String date) {
        return service.getUpdates(date);
    }
}
