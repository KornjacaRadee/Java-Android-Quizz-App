package com.example.projekat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Korak extends AppCompatActivity {

    private TextView textView20;
    private TextView[] textViews;
    private EditText answerEditText;
    private TextView scoreTextView;

    private CountDownTimer countDownTimer;
    private final long COUNTDOWN_INTERVAL = 1000; // interval brojanja u milisekundama
    private final long TIMER_DURATION = 10000; // trajanje tajmera u milisekundama

    private int currentScore = 0;
    private final int SCORE_INCREMENT = 20;

    private boolean[] textViewOpened;

    private FirebaseFirestore db;
    private DocumentReference documentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korak);

        textView20 = findViewById(R.id.textView20);
        textViews = new TextView[]{
                findViewById(R.id.textView21),
                findViewById(R.id.textView23),
                findViewById(R.id.textView24),
                findViewById(R.id.textView25),
                findViewById(R.id.textView26),
                findViewById(R.id.textView27),
                findViewById(R.id.neki1)
        };
        answerEditText = findViewById(R.id.editTextText8);
        scoreTextView = findViewById(R.id.score);

        db = FirebaseFirestore.getInstance();
        documentRef = db.collection("KorakPoKorak").document("KorakpoKorak");

        textViewOpened = new boolean[textViews.length];

        for (int i = 0; i < textViews.length; i++) {
            final int index = i;
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showTextAndStartTimer(index);
                    textViewOpened[index] = true;
                }
            });
        }

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
                openActivityAsocijacije();
            }
        });
    }

    private void showTextAndStartTimer(final int index) {
        final TextView textView = textViews[index];
        textView.setVisibility(View.VISIBLE);

        documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String fieldKey = "Korak" + (index + 1);
                    String textToShow = documentSnapshot.getString(fieldKey);
                    textView.setText(textToShow);
                    countDownTimer.start();
                } else {
                    Log.d("Korak", "Dokument ne postoji");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Korak", "Greška pri pristupu bazi podataka", e);
            }
        });
    }

    private void showNotification() {
        Toast.makeText(this, "Unesite odgovor!", Toast.LENGTH_SHORT).show();
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();
        int scoreIncrement = SCORE_INCREMENT;

        for (boolean opened : textViewOpened) {
            if (opened) {
                scoreIncrement -= 2;
            }
        }

        if (userAnswer.equalsIgnoreCase("zubi")) {
            currentScore += scoreIncrement;
            Toast.makeText(this, "Odgovor je tačan! Dobili ste " + scoreIncrement + " bodova.", Toast.LENGTH_SHORT).show();

            disableTextViews();
            answerEditText.setEnabled(false);

            Button sledecibtn = findViewById(R.id.sledecibtn);
            sledecibtn.setEnabled(true);
        } else {
            Toast.makeText(this, "Odgovor nije tačan!", Toast.LENGTH_SHORT).show();
        }

        scoreTextView.setText(String.valueOf(currentScore));
        answerEditText.setText("");
    }

    private void disableTextViews() {
        for (TextView textView : textViews) {
            textView.setEnabled(false);
        }
    }

    public void openActivityAsocijacije() {
        Intent intent = new Intent(this, Asocijacije.class);
        startActivity(intent);
    }
}
