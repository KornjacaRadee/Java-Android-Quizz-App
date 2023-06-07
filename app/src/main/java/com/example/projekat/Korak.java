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
    private TextView neki1;

    private TextView textView20;
    private EditText answerEditText;
    private TextView scoreTextView;

    private CountDownTimer countDownTimer;
    private final long COUNTDOWN_INTERVAL = 1000; // interval brojanja u milisekundama
    private final long TIMER_DURATION = 10000; // trajanje tajmera u milisekundama

    private int currentScore = 0;
    private final int SCORE_INCREMENT = 20;

    private boolean textView21Opened = false;
    private boolean textView23Opened = false;
    private boolean textView24Opened = false;
    private boolean textView25Opened = false;
    private boolean textView26Opened = false;
    private boolean textView27Opened = false;
    private boolean textView28Opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korak);




        textView20 = findViewById(R.id.textView20);
        textView21 = findViewById(R.id.textView21);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView26 = findViewById(R.id.textView26);
        textView27 = findViewById(R.id.textView27);
        neki1 = findViewById(R.id.neki1);
        answerEditText = findViewById(R.id.editTextText8);
        scoreTextView = findViewById(R.id.score);

        textView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer(textView21);
                textView21Opened = true;
            }
        });

        textView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer(textView23);
                textView23Opened = true;
            }
        });

        textView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer(textView24);
                textView24Opened = true;
            }
        });

        textView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer(textView25);
                textView25Opened = true;
            }
        });

        textView26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer(textView26);
                textView26Opened = true;
            }
        });

        textView27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer(textView27);
                textView27Opened = true;
            }
        });

        neki1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTextAndStartTimer(neki1);
                textView28Opened = true;
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
        sledecibtn.setEnabled(false);
        sledecibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAcocijacije();
            }
        });
    }

    public void openActivityAcocijacije() {
        Intent intent = new Intent(this, Asocijacije.class);
        startActivity(intent);
    }

    private void showTextAndStartTimer(final TextView textView) {
        textView.setVisibility(View.VISIBLE);
        textView.setText("Ovde se prikazuje određeni tekst");

        countDownTimer.start();
    }

    private void showNotification() {
        Toast.makeText(this, "Unesite odgovor!", Toast.LENGTH_SHORT).show();
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();
        int scoreIncrement = 20;

        if (textView21Opened) {
            scoreIncrement -= 0; // Oduzmi 2 boda ako je textView21 otvoren
        }
        if (textView23Opened) {
            scoreIncrement -= 2; // Oduzmi 2 boda ako je textView23 otvoren
        }
        if (textView24Opened) {
            scoreIncrement -= 2; // Oduzmi 2 boda ako je textView24 otvoren
        }
        if (textView25Opened) {
            scoreIncrement -= 2; // Oduzmi 2 boda ako je textView25 otvoren
        }
        if (textView26Opened) {
            scoreIncrement -= 2; // Oduzmi 2 boda ako je textView26 otvoren
        }
        if (textView27Opened) {
            scoreIncrement -= 2; // Oduzmi 2 boda ako je textView27 otvoren
        }
        if (textView28Opened) {
            scoreIncrement -= 5; // Oduzmi 2 boda ako je textView27 otvoren
        }

        if (userAnswer.equalsIgnoreCase("tacan_odgovor")) {
            currentScore += scoreIncrement; // Dodaj ukupan broj bodova
            Toast.makeText(this, "Odgovor je tačan! Dobili ste " + scoreIncrement + " bodova.", Toast.LENGTH_SHORT).show();

            disableTextViews(); // Onemogući klikabilnost TextView elemenata
            answerEditText.setEnabled(false); // Onemogući unos teksta

            Button sledecibtn = findViewById(R.id.sledecibtn);
            sledecibtn.setEnabled(true); // Omogući klikabilnost dugmeta "sledecibtn"
        } else {
            Toast.makeText(this, "Odgovor nije tačan!", Toast.LENGTH_SHORT).show();
        }

        scoreTextView.setText(String.valueOf(currentScore));
        answerEditText.setText("");
    }

    private void disableTextViews() {
        textView21.setEnabled(false);
        textView23.setEnabled(false);
        textView24.setEnabled(false);
        textView25.setEnabled(false);
        textView26.setEnabled(false);
        textView27.setEnabled(false);
        neki1.setEnabled(false);
    }
}




