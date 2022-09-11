package com.example.projectforyandexx2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "file_validation")
@Data
@NoArgsConstructor
@SuperBuilder
public class FileValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String url;
    @Column(name = "parent_id")
    private String parentId;
    private String type;
    private Integer size;
    @Column(name = "update_date")
    private Long updateDate;
}
