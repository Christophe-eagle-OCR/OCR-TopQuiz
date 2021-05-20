package com.perez.christophe.topquiz.model;

import java.util.List;

/**
 * Created by christophe on 12/05/2021.
 */
public class Question {

    /**
     * mQuestion: c'est le texte de la question
     * mChoiceList : c'est la liste des réponses proposées
     * mAnswerIndex : c'est l'index de la reponse dans la liste prééedente
     */
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;


    // todo il ya une difference avec le code du cours
    // this.setQuestion(question);
    // this.setChoiceList(choiceList);
    // this.setAnswerIndex(answerIndex);
    //
    public Question(String question, List<String> choiceList, int answerIndex) {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }


    public void setChoiceList(List<String> choiceList) {
        // condition if  : pour verifier que la liste des reponses contienne au moins une entrée
        if (choiceList == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        // condition if pour vérifier que l'index soit compris entre [0; nombre de reponse possible[
        if (answerIndex < 0 || answerIndex >= mChoiceList.size()) {
            throw new IllegalArgumentException("Answer index is out of bound");
        }
        mAnswerIndex = answerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }
}

