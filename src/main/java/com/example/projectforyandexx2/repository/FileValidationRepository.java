package com.example.projectforyandexx2.repository;

import com.example.projectforyandexx2.model.FileValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FileValidationRepository extends JpaRepository<FileValidation, Integer> {
    List<FileValidation> findAllByUuid(String uuid);

    List<FileValidation> findAllByUuidAndUpdateDateBetween(String uuid, Long updateDate, Long updateDate2);

    void deleteAllByUuidIn(Collection<String> uuid);
}
