package com.demo.springboot.domain.dto;

import java.time.ZonedDateTime;

public class FileData {
    private String fileName;
    private Long size;
    private ZonedDateTime creationDate;
    public FileData(String fileName, Long size, ZonedDateTime creationDate) {
        this.fileName = fileName;
        this.size = size;
        this.creationDate = creationDate;
    }
    public String getFileName() { return fileName; }
    public Long getSize() { return size;}
    public ZonedDateTime getCreationDate() { return creationDate;}
}
