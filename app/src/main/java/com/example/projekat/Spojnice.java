package com.example.projekat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spojnice extends AppCompatActivity implements View.OnClickListener {

    private List<Pair<Button, Button>> buttonPairs;
    private List<Button> questionButtons;
    private List<Button> answerButtons;
    private Button selectedQuestionButton;
    private Button selectedAnswerButton;
    private int score = 0;
    private EditText editTextScore;
    private FirebaseFirestore firestore;

    private Map<Button, String> buttonFieldMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spojnice);
        Log.d("Spojnice", "onCreate: Starting game...");



        firestore = FirebaseFirestore.getInstance();

        fetchValuesFromFirebase();
        editTextScore = findViewById(R.id.score);

        buttonPairs = new ArrayList<>();
        buttonPairs.add(new Pair<>(findViewById(R.id.button12), findViewById(R.id.button18)));
        buttonPairs.add(new Pair<>(findViewById(R.id.button13), findViewById(R.id.button19)));
        buttonPairs.add(new Pair<>(findViewById(R.id.button14), findViewById(R.id.button20)));
        buttonPairs.add(new Pair<>(findViewById(R.id.button15), findViewById(R.id.button21)));
        buttonPairs.add(new Pair<>(findViewById(R.id.button17), findViewById(R.id.button22)));

        questionButtons = new ArrayList<>();
        answerButtons = new ArrayList<>();
        buttonFieldMap = new HashMap<>();




        for (Pair<Button, Button> pair : buttonPairs) {
            Button questionButton = pair.first;
            Button answerButton = pair.second;
            questionButton.setOnClickListener(this);
            answerButton.setOnClickListener(this);
            questionButtons.add(questionButton);
            answerButtons.add(answerButton);


        }
        }




    @Override
    public void onClick(View v) {

        Button button30 = findViewById(R.id.button30);
        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityKozna();
            }
        });
        if (v instanceof Button) {
            Button clickedButton = (Button) v;

            if (selectedQuestionButton == null) {
                if (questionButtons.contains(clickedButton)) {
                    selectedQuestionButton = clickedButton;
                    selectedQuestionButton.setBackgroundColor(Color.YELLOW);
                }
            } else {
                if (questionButtons.contains(clickedButton)) {
                    selectedQuestionButton.setBackgroundColor(Color.TRANSPARENT);
                    selectedQuestionButton = clickedButton;
                    selectedQuestionButton.setBackgroundColor(Color.YELLOW);
                } else if (answerButtons.contains(clickedButton)) {
                    selectedAnswerButton = clickedButton;
                    Pair<Button, Button> selectedPair = getPairForButtons(selectedQuestionButton, selectedAnswerButton);

                    if (selectedPair != null) {
                        score += 2;
                        Toast.makeText(this, "Tačno!", Toast.LENGTH_SHORT).show();
                        selectedQuestionButton.setEnabled(false);
                        selectedAnswerButton.setEnabled(false);
                        selectedQuestionButton.setBackgroundColor(Color.GREEN);
                        selectedAnswerButton.setBackgroundColor(Color.GREEN);

                        if (score == buttonPairs.size() * 2) {
                            Toast.makeText(this, "Čestitamo! Svi odgovori su tačno povezani!", Toast.LENGTH_SHORT).show();
                            openActivityKozna();
                        }
                    } else {
                        Toast.makeText(this, "Niste uspeli!", Toast.LENGTH_SHORT).show();
                    }

                    selectedQuestionButton.setBackgroundColor(Color.TRANSPARENT);
                    selectedAnswerButton.setBackgroundColor(Color.TRANSPARENT);
                    selectedQuestionButton = null;
                    selectedAnswerButton = null;
                }
            }

            editTextScore.setText(String.valueOf(score));
        }
    }


    private void fetchValuesFromFirebase() {
        // Fetch value for questionButton 12 from Firestore
        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("1");
                        ((Button) findViewById(R.id.button12)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        // Fetch value for questionButton 13 from Firestore
        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("2");
                        ((Button) findViewById(R.id.button13)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        // Fetch value for questionButton 14 from Firestore
        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("3");
                        ((Button) findViewById(R.id.button14)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        // Slično uradite za preostale dugmadi
        // ...

        // Fetch value for answerButton A from Firestore
        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("4");
                        ((Button) findViewById(R.id.button15)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        // Fetch value for answerButton B from Firestore
        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("5");
                        ((Button) findViewById(R.id.button17)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });
        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("A");
                        ((Button) findViewById(R.id.button18)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("B");
                        ((Button) findViewById(R.id.button19)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("C");
                        ((Button) findViewById(R.id.button20)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("D");
                        ((Button) findViewById(R.id.button21)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });

        firestore.collection("Spojnice")
                .document("spojnice")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String questionText = documentSnapshot.getString("E");
                        ((Button) findViewById(R.id.button22)).setText(questionText);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle error while fetching value from Firestore for questionButton 12
                });



    }

    private Pair<Button, Button> getPairForButtons(Button questionButton, Button answerButton) {
        for (Pair<Button, Button> pair : buttonPairs) {
            if (pair.first == questionButton && pair.second == answerButton) {
                return pair;
            }
        }
        return null;
    }



    public void openActivityKozna() {
        Intent intent = new Intent(this, KoznaActivity.class);
        startActivity(intent);
    }
}
