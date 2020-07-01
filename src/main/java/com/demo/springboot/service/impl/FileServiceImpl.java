package com.demo.springboot.service.impl;

import com.demo.springboot.domain.dto.AnswerDto;
import com.demo.springboot.domain.dto.FileData;
import com.demo.springboot.domain.model.DocumentComponent;
import com.demo.springboot.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired
    private DocumentComponent documentComponent;
    @Override
    public FileData createFile(ArrayList<AnswerDto> answerDto, String path) {
        String fileName ="quiz_" + ZonedDateTime.now().toEpochSecond() + ".pdf";
        String fileDestination = path + fileName;
        try {
            Files.createDirectories(Paths.get(path));
            documentComponent.createDocument(answerDto, fileDestination);
            FileData fileData = new FileData(fileName, getFileSize(fileDestination), ZonedDateTime.now());
            return fileData;
        } catch (IOException e) {
            LOGGER.error("i can't save data");
        }
        return null;
    }
    private Long getFileSize(String fileDestination) throws IOException {
        Path filePath = Paths.get(fileDestination);
        BasicFileAttributes fileAttributes = Files.readAttributes(filePath, BasicFileAttributes.class);
        return fileAttributes.size();
    }
}
