package com.demo.springboot.service;

import com.demo.springboot.domain.dto.AnswerDto;
import com.demo.springboot.domain.dto.FileData;

import java.util.ArrayList;
import java.util.List;

public interface FileService {
    FileData createFile(ArrayList<AnswerDto> answerDto, String path);
}
