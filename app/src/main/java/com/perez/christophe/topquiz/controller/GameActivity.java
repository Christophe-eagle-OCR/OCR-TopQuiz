package com.perez.christophe.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.perez.christophe.topquiz.R;
import com.perez.christophe.topquiz.model.Question;
import com.perez.christophe.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextView;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;  // 1/3 score de l'utilisateur
    private  int nNumberOfQuestions; // 1/3 nbr de questions posées à l'utilisateur

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();

        mScore = 0; // 2/3 score de l'utilisateur, ici score egal 0
        nNumberOfQuestions = 4;   // 2/3 nbr de questions posées à l'utilisateur, ici 4 questions

        // Wire widgets
        mQuestionTextView = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswerButton1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        // use the tag property to "name" the buttons
        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        // enregistre pour chaque bouton l'actvité courante pour appeler la methodse onClick
        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getNextQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    // cette methode va etre appelé quelque soit le bouton sur lequel l'utilisateur va clicker
    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            mScore++; // 3/3 score de l'utilisateur
        } else {
            //Wrong answer
            Toast.makeText(this,"Wrong answer!",Toast.LENGTH_SHORT).show();
        }

        // Quand l'utilisateur a répondu 4 fois de suite pour donner les reponses, on arrete le jeuu
        if (--nNumberOfQuestions == 0) {
            // PM  : avec l'opératueur -- devant mNumberOfQuestions, on va décrementer lorsque l'utilisateur répond à une question
            //quand on arrive à 0 , il n'y a plus de question , c'est End the game

            endGame(); // methode pour gerer l'affichage dans la boite de dialogue à la fin du jeu

        } else {
            // sinon au continu le jeu , on va demander au model une nouvelle question et on va l'afficher à l'écran
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        }
    }

    // methode pour gerer l'affichage dans la boite de dialogue à la fin du jeu
    private void endGame () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " +mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity , ferme l'affichage courant du layout Activity_Game , et donc seul le layout activity_main apparaitra
                        // finish(); methode initiale mais on ajoute un intent ci dessous pour récuperer le score au démarrage du jeu
                        Intent intent = new Intent(); // on crée un intent
                        intent.putExtra(BUNDLE_EXTRA_SCORE,mScore); // on attache le score à l'intent stocké dans la variable  BUNDLE_EXTRA_SCORE
                        setResult(RESULT_OK,intent); // on précise à android que l'acivité c'est correctement terminée avec le score attaché à l'intent
                        finish(); // on termine l'activité
                    }
                })
                .create()
                .show();
    }

    private  void displayQuestion(final Question question) {
        mQuestionTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions(){
        Question question1 = new Question("quel est le nom du président actuel de la France?",
                Arrays.asList("François Hollande","Emmanuel Macron","Jacques Chirac","François Miterrand"),
                1);

        Question question2 = new Question("Combien de pays il y a dans l'union Européene?",
                Arrays.asList("15","24","28","32"),2);

        Question question3 = new Question("Qui est le créateur du système d'exploitation Android?",
                Arrays.asList("Andy Rubin","Steve Wozniak","Jake Wharton","Paul Smith"),0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }
}