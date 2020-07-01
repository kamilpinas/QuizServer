package com.demo.springboot.domain.dto;

public class Questions {
    private String id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswers;
    private String points;

    public Questions(String id, String question, String answerA, String answerB, String answerC, String answerD, String correctAnswers, String points) {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswers = correctAnswers;
        this.points = points;
    }

    public String getId() { return id; }
    public String getQuestion() {return question;}
    public String getAnswerA() {return answerA;}
    public String getAnswerB() { return answerB;}
    public String getAnswerC() { return answerC;}
    public String getAnswerD() { return answerD;}
    public String getCorrectAnswers() {return correctAnswers;}
    public String getPoints() { return points;}
}