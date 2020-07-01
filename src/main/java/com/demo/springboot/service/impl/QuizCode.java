package com.demo.springboot.service.impl;

import com.demo.springboot.domain.dto.Questions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class QuizCode {
    private static ArrayList<Questions> questionsList;
    private static final String fileName = "src/main/java/com/demo/springboot/domain/csv/quiz.csv";
    private static String SEPERATOR = "/n";
    private static final String SPLIT_CHAR = ";";

    public static int getRequiredPoints() {
        return REQUIRED_POINTS;
    }

    private static final int REQUIRED_POINTS = (countMax(readData()) / 2);

    public static ArrayList<Questions> readData() { // wczytywywanie csv
        BufferedReader br = null;
        questionsList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((SEPERATOR = br.readLine()) != null) {
                String[] tab = SEPERATOR.split(SPLIT_CHAR);
                Questions pyt = new Questions(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7]);
                questionsList.add(pyt);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Plik jest pusty");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return questionsList;
    }

    public static int checkAnswer(Integer[] yourAnswer, String correctAnswers, int points) {
        Arrays.sort(yourAnswer);
        String yourAnswers = "";
        for (int i = 0; i < yourAnswer.length; i++) {
            yourAnswers = yourAnswers + yourAnswer[i];
        }
        correctAnswers = correctAnswers.replaceAll(",", "");
        char[] yourAnswerArray = yourAnswers.toCharArray();
        char[] correctAnswersArray = correctAnswers.toCharArray();
        int countCorrect = 0;
        if (yourAnswers.length() == correctAnswers.length()) {
            for (int i = 0; i < yourAnswers.length(); i++) {
                if (yourAnswerArray[i] == correctAnswersArray[i]) {
                    countCorrect++;
                }
            }
        }
        if (countCorrect == correctAnswers.length()) {
            return points;
        } else {
            return 0;
        }
    }

    public static int countMax(ArrayList<Questions> quiz) {
        int maxPoints = 0;
        for (int i = 0; i < quiz.size(); i++) {
            maxPoints = maxPoints + Integer.parseInt(quiz.get(i).getPoints());
        }
        return maxPoints;
    }

    public static boolean ifPassed(int yourScore) {
        if (yourScore >= REQUIRED_POINTS) {
            return true;
        } else {
            return false;
        }
    }
}

