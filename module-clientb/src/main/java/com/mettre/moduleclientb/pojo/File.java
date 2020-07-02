package com.mettre.moduleclientb.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class File {

    private Long fileId;

    private String fileLink;

    private LocalDateTime creationTime;

    public File(String fileLink) {
        this.fileLink = fileLink;
        this.creationTime = LocalDateTime.now();
    }
}