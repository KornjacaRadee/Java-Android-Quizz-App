package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

public class Skocko extends AppCompatActivity {

    private TextView textView20;
    private TextView textView11;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skocko);

        Button buttonSkocko = findViewById(R.id.skocko);
        Button buttonSrce = findViewById(R.id.srce);
        Button buttonList = findViewById(R.id.list);
        Button buttonKocka = findViewById(R.id.kocka);
        Button buttonTref = findViewById(R.id.tref);
        Button buttonPik = findViewById(R.id.pik);





        EditText editText = findViewById(R.id.editTextText15);
        EditText editText1 = findViewById(R.id.editTextText13);
        EditText editText2 = findViewById(R.id.editTextText11);
        EditText editText3 = findViewById(R.id.editTextText10);
        EditText editText4 = findViewById(R.id.editTextText12);
        EditText editText5 = findViewById(R.id.editTextText14);


        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                String buttonValue = "";

                if (v.getId() == R.id.srce) {
                    buttonValue = buttonSrce.getText().toString();
                } else if (v.getId() == R.id.skocko) {
                    buttonValue = buttonSkocko.getText().toString();
                } else if (v.getId() == R.id.list) {
                    buttonValue = buttonList.getText().toString();
                } else if (v.getId() == R.id.kocka) {
                    buttonValue = buttonKocka.getText().toString();
                } else if (v.getId() == R.id.tref) {
                    buttonValue = buttonTref.getText().toString();
                } else if (v.getId() == R.id.pik) {
                    buttonValue = buttonPik.getText().toString();
                }

                String currentText = editText.getText().toString();
                String newText = currentText + buttonValue;
                editText.setText(newText);
            }

        };


        buttonSrce.setOnClickListener(buttonClickListener);
        buttonSkocko.setOnClickListener(buttonClickListener);
        buttonList.setOnClickListener(buttonClickListener);
        buttonKocka.setOnClickListener(buttonClickListener);
        buttonTref.setOnClickListener(buttonClickListener);
        buttonPik.setOnClickListener(buttonClickListener);



        Button button28 = findViewById(R.id.button28);
        TextView textView32 = findViewById(R.id.textView32);


        button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tačnaKombinacija = "1234"; // Promenite vrednost na željenu tačnu kombinaciju
                String unetaKombinacija = editText.getText().toString();
                int brojTačnihMesta = 0;
                int brojNetačnihMesta = 0;

                // Provera za svaki znak u kombinaciji
                for (int i = 0; i < unetaKombinacija.length(); i++) {
                    if (i < tačnaKombinacija.length()) {
                        char unetiKarakter = unetaKombinacija.charAt(i);
                        char tačanKarakter = tačnaKombinacija.charAt(i);

                        // Ako je znak na tačnom mestu
                        if (unetiKarakter == tačanKarakter) {
                            brojTačnihMesta++;
                        } else if (tačnaKombinacija.indexOf(unetiKarakter) != -1) {
                            // Ako znak postoji u tačnoj kombinaciji, ali nije na tačnom mestu
                            brojNetačnihMesta++;
                        }
                    } else {
                        // Ako je kombinacija duža od tačne kombinacije
                        brojNetačnihMesta++;
                    }
                }

                String rezultat = "Tačno mesta: " + brojTačnihMesta + ", Netačno mesta: " + brojNetačnihMesta;
                textView32.setText(rezultat);

                // Provera da li je uneta tačna kombinacija
                if (unetaKombinacija.equals(tačnaKombinacija)) {
                    // Unesena je tačna kombinacija

                    int brojPokušaja = 6 - (Integer.parseInt(textView20.getText().toString()) - 1);
                    int bodovi = 0;

                    if (brojPokušaja <= 2) {
                        bodovi = 20;
                    } else if (brojPokušaja > 2 || brojPokušaja <4) {
                        bodovi = 15;
                    } else if (brojPokušaja > 4 || brojPokušaja <6) {
                        bodovi = 10;
                    }

                    Toast.makeText(getApplicationContext(), "Rešenje je tačno! Osvojili ste " + bodovi + " bodova.", Toast.LENGTH_SHORT).show();

                    // Povećaj broj bodova
                    EditText editTextBodovi = findViewById(R.id.editTextText7);
                    int trenutniBrojBodova = Integer.parseInt(editTextBodovi.getText().toString());
                    int noviBrojBodova = trenutniBrojBodova + bodovi;
                    editTextBodovi.setText(String.valueOf(noviBrojBodova));
                } else if (Integer.parseInt(textView20.getText().toString()) == 1) {
                    // Niste uspeli da pogodite resenje u prvih 6 puta
                    Toast.makeText(getApplicationContext(), "Niste uspeli da pogodite resenje u prvih 6 puta.", Toast.LENGTH_SHORT).show();
                }
            }
        });




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


