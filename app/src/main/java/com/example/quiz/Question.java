package com.example.quiz;

import androidx.annotation.NonNull;

import java.util.List;

public class Question {

    private String question;
    private List<String> responsesList;
    private int correctAnswerIndex;

    @NonNull
    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", responsesList=" + responsesList +
                ", correctAnswerIndex=" + correctAnswerIndex +
                '}';
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getResponsesList() {
        return responsesList;
    }

    public void setResponsesList(List<String> responsesList) {
        this.responsesList = responsesList;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
