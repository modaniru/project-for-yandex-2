package com.example.projectforyandexx2.service.internalService;

import com.example.projectforyandexx2.exception.RestException;
import com.example.projectforyandexx2.model.File;
import com.example.projectforyandexx2.model.FileValidation;
import com.example.projectforyandexx2.repository.FileRepository;
import com.example.projectforyandexx2.repository.FileValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileInternalService {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    FileValidationRepository validationRepository;

    public void saveFileList(List<File> files) {
        HashMap<String, FileValidation> saveValid = new HashMap<>();
        for (File file : files) {
            List<File> roots = new ArrayList<>();
            roots.add(file);
            saveValid.put(file.getId(), toFileValidation(file));
            String pI = file.getParentId();
            while (pI != null) {
                File f = getFile(pI);
                f.setSize(f.getSize() + file.getSize());
                f.setDate(file.getDate());
                saveValid.put(f.getId(), toFileValidation(f));
                roots.add(f);
                pI = f.getParentId();
            }
            fileRepository.saveAll(roots);
        }
        List<FileValidation> save = new ArrayList<>(saveValid.values());
        validationRepository.saveAll(save);
    }

    public File getFile(String id) {
        return fileRepository.findById(id).orElseThrow(() -> new RestException("Item not found", HttpStatus.NOT_FOUND));
    }

    public List<File> delete(String id) {
        File file = fileRepository.findById(id).orElseThrow(() -> new RestException("Item not found", HttpStatus.NOT_FOUND));
        int deleteSize = file.getSize();
        String currentFileParentId = file.getParentId();
        List<File> saveFiles = new ArrayList<>();
        while (currentFileParentId != null) {
            File f = fileRepository.findById(currentFileParentId).orElseThrow(() -> new RestException("Item not found", HttpStatus.NOT_FOUND));
            f.setSize(f.getSize() - deleteSize);
            currentFileParentId = f.getParentId();
            saveFiles.add(f);
        }
        List<File> deleteFiles = new ArrayList<>(Arrays.asList(file));
        System.out.println(deleteFiles);
        for (int i = 0; i < deleteFiles.size(); i++) {
            List<File> child = fileRepository.findAllByParentId(deleteFiles.get(i).getId());
            deleteFiles.addAll(child);
        }
        fileRepository.saveAll(saveFiles);
        fileRepository.deleteAll(deleteFiles);
        return deleteFiles;
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
