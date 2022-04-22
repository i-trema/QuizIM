package com.example.quiz;

import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> questionsList;
    private int totalOfAllQuestions, nextQuestionIndex;

    @NonNull
    @Override
    public String toString() {
        return "QuestionBank{" +
                "questionsList=" + questionsList +
                ", totalOfAllQuestions=" + totalOfAllQuestions +
                ", nextQuestionIndex=" + nextQuestionIndex +
                '}';
    }

    public QuestionBank(List<Question> questionsList){
        //TODO
        Collections.shuffle(questionsList);
        this.questionsList = questionsList;
        this.totalOfAllQuestions = questionsList.size();


    }
    public Question getNextQuestion(){
        //TODO
        return questionsList.get(nextQuestionIndex);

    }
    public QuestionBank() {
    }

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList = questionsList;
    }

    public int getTotalOfAllQuestions() {
        return totalOfAllQuestions;
    }

    public void setTotalOfAllQuestions(int totalOfAllQuestions) {
        this.totalOfAllQuestions = totalOfAllQuestions;
    }

    public int getNextQuestionIndex() {

        return nextQuestionIndex;
    }

    public void setNextQuestionIndex(int nextQuestionIndex) {
        this.nextQuestionIndex = nextQuestionIndex;
    }
}
