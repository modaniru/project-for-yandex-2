package com.example.projectforyandexx2.controller;

import com.example.projectforyandexx2.dto.response.FileResponseDto;
import com.example.projectforyandexx2.model.Item;
import com.example.projectforyandexx2.service.externalService.FileExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class FileController {
    @Autowired
    FileExternalService fileExternalService;

    @PostMapping("/imports")
    public void save(@RequestBody Item item) {
        fileExternalService.saveItemList(item);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        fileExternalService.delete(id);
    }

    @GetMapping("/nodes/{id}")
    public FileResponseDto getFile(@PathVariable("id") String id) {
        return fileExternalService.getFile(id);
    }

    @GetMapping("/updates")
    public List<FileResponseDto> getUpdates(@RequestParam("date") String date) {
        return fileExternalService.getUpdates(date);
    }
}
