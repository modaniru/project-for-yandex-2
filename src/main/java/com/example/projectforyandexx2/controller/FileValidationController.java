package com.example.projectforyandexx2.controller;

import com.example.projectforyandexx2.dto.response.FileValidationResponseDto;
import com.example.projectforyandexx2.service.externalService.FileValidationExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class FileValidationController {
    @Autowired
    FileValidationExternalService service;

    @GetMapping("/node/{id}/history")
    public List<FileValidationResponseDto> getHistory(@PathVariable("id") String id
            , @RequestParam(required = false) String dateStart
            , @RequestParam(required = false) String dateEnd) {
        if (dateEnd == null || dateStart == null) {
            return service.getHistory(id);
        }
        return service.getHistory(id, dateStart, dateEnd);
    }
}
