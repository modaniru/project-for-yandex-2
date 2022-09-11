package com.example.projectforyandexx2.service.internalService;

import com.example.projectforyandexx2.model.FileValidation;
import com.example.projectforyandexx2.repository.FileValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileValidationInternalService {
    @Autowired
    private FileValidationRepository repository;

    public List<FileValidation> getFileHistory(String id) {
        return repository.findAllByUuid(id);
    }

    public List<FileValidation> getFileHistory(String id, Long dateStart, Long dateEnd) {
        dateEnd--;
        return repository.findAllByUuidAndUpdateDateBetween(id, dateStart, dateEnd);
    }

    @Transactional
    public void deleteItemHistory(List<String> ids) {
        repository.deleteAllByUuidIn(ids);
    }

    public void saveAll(List<FileValidation> valid){
        repository.saveAll(valid);
    }
}
