package com.demo.springboot.domain.dto;

import lombok.AllArgsConstructor;
import java.util.Arrays;

@AllArgsConstructor
public class AnswerDto {
    private int questionId;
    private boolean lastQuestion;
    private Integer[] selectedAnswers;

    public AnswerDto() { }
    public Integer[] getSelectedAnswers() {return selectedAnswers;}
    public int getQuestionId() { return questionId;}
    @Override
    public String toString() {
        return "{\"AnswerData\":{"
                + "\"questionId\":\"" + questionId + ""
                + ", \"lastQuestion\":\"" + lastQuestion + "\""
                + ", \"selectedAnswers\":\"" + Arrays.toString(selectedAnswers) + "\""
                + "}}";
    }
}
