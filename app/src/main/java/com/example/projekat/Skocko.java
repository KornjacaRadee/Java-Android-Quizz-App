package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Skocko extends AppCompatActivity {

    private TextView textView20;
    private TextView textView11;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skocko);


        Button sledecibtn = findViewById(R.id.button32);

        sledecibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySlova();
            }
        });

        Button button32 = findViewById(R.id.button32);
        handler = new Handler();


        runnable = new Runnable() {
            private int timeLeft = 30;
            Button button29 = findViewById(R.id.button32);

            @Override
            public void run() {
                timeLeft--;
                textView20.setText(String.valueOf(timeLeft));

                if (timeLeft <= 0) {
                    // Vreme je isteklo
                    Toast.makeText(getApplicationContext(), "Vreme je isteklo!", Toast.LENGTH_SHORT).show();

                    button32.performClick();


                } else {
                    // Ponovo pokreće tajmer za svaku sekundu
                    handler.postDelayed(this, 1000);
                }
            }
        };

        handler.postDelayed(runnable, 1000);


        textView20 = findViewById(R.id.textView20);

    }


    public void openActivitySlova() {
        Intent intent = new Intent(this,Slova.class);
        startActivity(intent);
    }
    }


