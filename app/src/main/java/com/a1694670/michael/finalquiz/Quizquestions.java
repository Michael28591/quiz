package com.a1694670.michael.finalquiz;

/**
 * Created by admin on 2017-05-12.
 */

public class Quizquestions {

    private String question,chA,chB,chC,chD,correctAns;


    public Quizquestions()
    {}

    public Quizquestions(String question,String chA,String chB,String chC,String chD,String correctAns)
    {
        this.question = question;
        this.chA = chA;
        this.chB = chB;
        this.chC = chC;
        this.chD = chD;
        this.correctAns = correctAns;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestions(String questions) {
        this.question = questions;
    }

    public String getChA() {
        return chA;
    }

    public void setChA(String chA) {
        this.chA = chA;
    }

    public String getChB() {
        return chB;
    }

    public void setChB(String chB) {
        this.chB = chB;
    }

    public String getChC() {
        return chC;
    }

    public void setChC(String chC) {
        this.chC = chC;
    }

    public String getChD() {
        return chD;
    }

    public void setChD(String chD) {
        this.chD = chD;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }
}
