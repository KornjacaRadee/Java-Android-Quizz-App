package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.os.Handler;

public class Korak extends AppCompatActivity {


    private TextView textView21;
    private TextView textView23;
    private TextView textView24;
    private TextView textView25;
    private TextView textView26;
    private TextView textView27;

    private TextView textView20;
    private EditText answerEditText;
    private TextView scoreTextView;

    private CountDownTimer countDownTimer;
    private final long COUNTDOWN_INTERVAL = 1000; // interval brojanja u milisekundama
    private final long TIMER_DURATION = 10000; // trajanje tajmera u milisekundama

    private int currentScore = 0;
    private final int SCORE_INCREMENT = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korak);

        textView21 = findViewById(R.id.textView21);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView26 = findViewById(R.id.textView26);
        textView27 = findViewById(R.id.textView27);
        answerEditText = findViewById(R.id.editTextText8);
        scoreTextView = findViewById(R.id.score);

        textView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer();
            }
        });
        

        countDownTimer = new CountDownTimer(TIMER_DURATION, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView20.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView20.setText("0");
                showNotification();
            }
        };

        answerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkAnswer();
                    return true;
                }
                return false;
            }
        });


        Button sledecibtn = findViewById(R.id.sledecibtn);

        sledecibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAcocijacije();
            }
        });

    }

        /*Button button29 = findViewById(R.id.sledecibtn);
        handler = new Handler();


        *//*runnable = new Runnable() {
            private int timeLeft = 30;
            Button button29 = findViewById(R.id.sledecibtn);

            @Override
            public void run() {
                timeLeft--;
                textView20.setText(String.valueOf(timeLeft));

                if (timeLeft <= 0) {
                    // Vreme je isteklo
                    Toast.makeText(getApplicationContext(), "Vreme je isteklo!", Toast.LENGTH_SHORT).show();

                    sledecibtn.performClick();


                } else {
                    // Ponovo pokreće tajmer za svaku sekundu
                    handler.postDelayed(this, 1000);
                }
            }
        };*//*

        handler.postDelayed(runnable, 1000);


        textView20 = findViewById(R.id.textView20);*/




    public void openActivityAcocijacije() {
        Intent intent = new Intent(this, Asocijacije.class);
        startActivity(intent);
    }


    private void showTextAndStartTimer() {
        textView21.setVisibility(View.VISIBLE);
        textView21.setText("Ovde se prikazuje određeni tekst");
        countDownTimer.start();
    }



    private void showNotification() {
        Toast.makeText(this, "Unesite odgovor!", Toast.LENGTH_SHORT).show();
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();

        if (userAnswer.equalsIgnoreCase("tacan_odgovor")) {
            currentScore += SCORE_INCREMENT;
            Toast.makeText(this, "Odgovor je tačan!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Odgovor nije tačan!", Toast.LENGTH_SHORT).show();
        }

        scoreTextView.setText(String.valueOf(currentScore));
        answerEditText.setText("");
    }
    }

