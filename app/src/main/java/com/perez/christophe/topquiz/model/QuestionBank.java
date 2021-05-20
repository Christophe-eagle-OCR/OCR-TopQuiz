package com.perez.christophe.topquiz.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by christophe on 18 mai 2021.
 * sert à recuperer toute la liste de questions possible
 * a chaque nouvelle partie les questions sont differentes et affichées dans un ordre aléatoire
 */

public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;

        //Shuffle (mélanger) the question list
        Collections.shuffle(mQuestionList);

        mNextQuestionIndex = 0;
    }

    /**
     * fournir une nouvelle question quand on le lui demandera
     * TODO methode getNextQuestion peut etre à renommer par getQuestion
     */
    public Question getNextQuestion() {
        // Ensure we Loop over the questions and return a new one at each call
        if (mNextQuestionIndex == mQuestionList.size()) {
            mNextQuestionIndex = 0;
        }

        // Please note the post-incrementation
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
