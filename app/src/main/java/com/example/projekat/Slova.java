package com.example.projekat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Slova extends AppCompatActivity {

    private TextView textView34;
    private TextView textView5;
    private TextView textView6;
    private TextView textView4;

    private TextView textView2;
    private TextView textView3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private EditText editTextText9;
    private EditText editTextText7;
    private int correctResult = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slova);

        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        editTextText9 = findViewById(R.id.editTextText9);
        editTextText7 = findViewById(R.id.editTextText7);

        textView34 = findViewById(R.id.textView34);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        Button button31 = findViewById(R.id.button31);

        setRandomNumber(textView34, 1, 9);
        setRandomNumber(textView2, 1, 9);
        setRandomNumber(textView3, 1, 9);
        setRandomNumber(textView4, 1, 9);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                String currentText = editTextText9.getText().toString();
                editTextText9.setText(currentText + buttonText);
            }
        };

        button4.setOnClickListener(buttonClickListener);
        button5.setOnClickListener(buttonClickListener);
        button6.setOnClickListener(buttonClickListener);
        button7.setOnClickListener(buttonClickListener);
        button8.setOnClickListener(buttonClickListener);
        button9.setOnClickListener(buttonClickListener);

        button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextText9.getText().toString().trim();

                if (!inputText.isEmpty()) {
                    try {
                        int userInput = Integer.parseInt(inputText);
                        int result = calculateResult();

                        if (userInput == result) {
                            addPoints(15);
                        } else {
                            showMessage("Rešenje nije tačno.");
                        }
                    } catch (NumberFormatException e) {
                        showMessage("Unesite validan broj.");
                    }
                } else {
                    showMessage("Unesite broj u polje.");
                }
            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFixedNumber(textView5, new int[]{10, 15, 20});
                setTextViewValueInEditText(textView5);
            }
        });

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFixedNumber(textView6, new int[]{50, 75, 100});
                setTextViewValueInEditText(textView6);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewValueInEditText(textView2);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewValueInEditText(textView3);
            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewValueInEditText(textView4);
            }
        });

        textView34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewValueInEditText(textView34);
            }
        });

        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySpojnice();
            }
        });
    }

    private void setRandomNumber(TextView textView, int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        textView.setText(String.valueOf(randomNumber));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(randomNumber));
            }
        });
    }

    private void setFixedNumber(TextView textView, int[] values) {
        Random random = new Random();
        int randomIndex = random.nextInt(values.length);
        int fixedNumber = values[randomIndex];
        textView.setText(String.valueOf(fixedNumber));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(fixedNumber));
            }
        });
    }

    private void setTextViewValueInEditText(TextView textView) {
        String textViewText = textView.getText().toString();
        String currentText = editTextText9.getText().toString();
        editTextText9.setText(currentText + textViewText);
    }

    private int calculateResult() {
        // Implement your calculation logic here
        // You can access the values from textView2, textView3, textView4, and textView34
        // For example:
        int value1 = Integer.parseInt(textView2.getText().toString());
        int value2 = Integer.parseInt(textView3.getText().toString());
        int value3 = Integer.parseInt(textView4.getText().toString());
        int value4 = Integer.parseInt(textView34.getText().toString());

        // Perform your calculations
        int result = value1 + value2 - value3 * value4;

        return result;
    }

    private void addPoints(int points) {
        String currentPointsText = editTextText7.getText().toString();
        int currentPoints = Integer.parseInt(currentPointsText);
        int newPoints = currentPoints + points;
        editTextText7.setText(String.valueOf(newPoints));
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void openActivitySpojnice() {
        Intent intent = new Intent(this, Spojnice.class);
        startActivity(intent);
    }
}
