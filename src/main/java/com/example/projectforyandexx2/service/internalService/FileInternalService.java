package com.example.projectforyandexx2.service.internalService;

import com.example.projectforyandexx2.exception.RestException;
import com.example.projectforyandexx2.model.File;
import com.example.projectforyandexx2.model.FileValidation;
import com.example.projectforyandexx2.repository.FileRepository;
import com.example.projectforyandexx2.service.externalService.FileValidationExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileInternalService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileValidationExternalService fileValidationExternalService;

    public void saveFileList(List<File> files) {
        HashMap<String, FileValidation> history = new HashMap<>();
        for (File file : files) {
            List<File> rootFiles = new ArrayList<>();
            rootFiles.add(file);
            history.put(file.getId(), toFileValidation(file));
            String pI = file.getParentId();
            while (pI != null) {
                File currentFile = getFile(pI);
                currentFile.setSize(currentFile.getSize() + file.getSize());
                currentFile.setDate(file.getDate());
                history.put(currentFile.getId(), toFileValidation(currentFile));
                rootFiles.add(currentFile);
                pI = currentFile.getParentId();
            }
            fileRepository.saveAll(rootFiles);
        }
        List<FileValidation> save = new ArrayList<>(history.values());
        fileValidationExternalService.saveAll(save);
    }

    public File getFile(String id) {
        return fileRepository.findById(id).orElseThrow(() -> new RestException("Item not found", HttpStatus.NOT_FOUND));
    }

    public void delete(String id) {
        File file = fileRepository.findById(id).orElseThrow(() -> new RestException("Item not found", HttpStatus.NOT_FOUND));
        int deleteSize = file.getSize();
        String currentFileParentId = file.getParentId();
        List<File> saveFiles = new ArrayList<>();
        while (currentFileParentId != null) {
            File currentFile = fileRepository.findById(currentFileParentId).orElseThrow(() -> new RestException("Item not found", HttpStatus.NOT_FOUND));
            currentFile.setSize(currentFile.getSize() - deleteSize);
            currentFileParentId = currentFile.getParentId();
            saveFiles.add(currentFile);
        }
        List<File> deleteFiles = new ArrayList<>(Arrays.asList(file));
        for (int i = 0; i < deleteFiles.size(); i++) {
            List<File> child = fileRepository.findAllByParentId(deleteFiles.get(i).getId());
            deleteFiles.addAll(child);
        }
        fileRepository.saveAll(saveFiles);
        fileRepository.deleteAll(deleteFiles);
        fileValidationExternalService.deleteItemHistory(deleteFiles.stream().map(File::getId).collect(Collectors.toList()));
    }

    public List<File> getUpdates(Long dateMinusDay, Long date) {
        return fileRepository.findAllByDateBetween(dateMinusDay, date);
    }

    private FileValidation toFileValidation(File file) {
        return FileValidation.builder().uuid(file.getId())
                .parentId(file.getParentId())
                .size(file.getSize())
                .type(file.getType())
                .url(file.getUrl())
                .updateDate(file.getDate()).build();
    }

    public List<File> getAllChild(String id) {
        return fileRepository.findAllByParentId(id);
    }
}
