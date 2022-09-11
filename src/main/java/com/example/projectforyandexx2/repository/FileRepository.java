package com.example.projectforyandexx2.repository;

import com.example.projectforyandexx2.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    List<File> findAllByParentId(String parentId);

    List<File> findAllByDateBetween(Long date, Long date2);
}
