package com.demo.springboot.domain.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReturnQuestion {
    int points;
    String question;
    String[] answers = new String[4];
    boolean lastQuestion;

    public ReturnQuestion(String question, String answerA, String answerB, String answerC, String answerD, String points, boolean lastQuestion) {
        this.question = question;
        answers[0] = answerA;
        answers[1] = answerB;
        answers[2] = answerC;
        answers[3] = answerD;
        this.points = Integer.parseInt(points);
        this.lastQuestion = lastQuestion;
    }
    public int getPoints() { return points;}
    public String getQuestion() { return question;}
    public String[] getAnswers() { return answers;}
    public boolean isLastQuestion() {
        return lastQuestion;
    }
}
