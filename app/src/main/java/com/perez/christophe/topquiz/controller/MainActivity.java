package com.perez.christophe.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.perez.christophe.topquiz.R;
import com.perez.christophe.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser; // 1/3 : pour contenir les information de mon utilisateur
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    // pour recuperer le renvoie du resultat du score de GAmeActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch (chercher) the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = new User(); // 2/3 : pour initialiser la variable mUser

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // active the button when writed text
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // pour intercepter le click du bouton mPlayButton
        // ici c'est le bouton de demarrage du jeu le bouton mPlayButton

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // user clicked the button for change first view for second view
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                // startActivity(gameActivityIntent); methode initiale mais est remplacée par la methode ci dessous pour récuperer le score au démarrage du jeu
                startActivityForResult(gameActivityIntent,GAME_ACTIVITY_REQUEST_CODE);

                // 3/3 : pour enregistrer le prénon de l'utilisateur
                // for memory the firstName when user clicked button on first view
                // je recupere le prénon qui se situe dans le champ mNameInput ( et sera stocké dans la variable firstmane ci dessous)
                // puis je valorise mon utilisateur mUser avec le setteur en lui passant en parametre le firstname
                String firstname = mNameInput.getText().toString();
                mUser.setFirstname(firstname);
            }
        });



    }
}