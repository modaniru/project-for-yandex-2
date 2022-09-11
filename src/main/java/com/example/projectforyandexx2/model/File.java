package com.example.projectforyandexx2.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file")
@Data
@NoArgsConstructor
public class File {
    @Id
    private String id;
    private String type;
    private String url;
    @Column(name = "parent_id")
    private String parentId;
    private int size;
    @Column(name = "update_date")
    private Long date;
}
