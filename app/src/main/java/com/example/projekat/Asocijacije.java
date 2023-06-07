package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.os.Handler;


public class Asocijacije extends AppCompatActivity {

    private int bodovi = 0;
    private int bod = 7;

    private boolean textView9Clicked = false;
    private boolean textView11Clicked = false;
    private boolean textView12Clicked = false;
    private boolean textView13Clicked = false;

    private boolean textView14Clicked = false;
    private boolean textView18Clicked = false;
    private boolean textView35Clicked = false;
    private boolean textView19Clicked = false;

    private boolean textView36Clicked = false;
    private boolean textView37Clicked = false;
    private boolean textView38Clicked = false;
    private boolean textView39Clicked = false;

    private boolean textView40Clicked = false;
    private boolean textView42Clicked = false;
    private boolean textView43Clicked = false;
    private boolean textView41Clicked = false;
    private TextView bodoviTextView;

    private TextView textView20;
    private TextView textView11;
    private Handler handler;
    private Runnable runnable;









    private List<TextView> neotkriveniTextViews = new ArrayList<>();
    private List<TextView> neotkriveniTextViews1 = new ArrayList<>();
    private List<TextView> neotkriveniTextViews2 = new ArrayList<>();
    private List<TextView> neotkriveniTextViews3 = new ArrayList<>();

    private List<TextView> kolonaA = new ArrayList<>();
    private List<TextView> kolonaB = new ArrayList<>();
    private List<TextView> kolonaC = new ArrayList<>();
    private List<TextView> kolonaD = new ArrayList<>();

    private int kolonaAotvoreno = 0;
    private int kolonaBotvoreno = 0;
    private int kolonaCotvoreno = 0;
    private int kolonaDotvoreno = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asociacije);



        Button sledecibtn1 = findViewById(R.id.button29);

        sledecibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySkocko();
            }
        });










        textView20 = findViewById(R.id.textView20);
        handler = new Handler();



        runnable = new Runnable() {
            private int timeLeft = 30;
            Button button29 = findViewById(R.id.button29);
            @Override
            public void run() {
                timeLeft--;
                textView20.setText(String.valueOf(timeLeft));

                if (timeLeft <= 0) {
                    // Vreme je isteklo
                    Toast.makeText(getApplicationContext(), "Vreme je isteklo!", Toast.LENGTH_SHORT).show();

                    button29.performClick();



                } else {
                    // Ponovo pokreće tajmer za svaku sekundu
                    handler.postDelayed(this, 1000);
                }
            }
        };

        handler.postDelayed(runnable, 1000);

        bodoviTextView = findViewById(R.id.score);
        textView20 = findViewById(R.id.textView20);



        neotkriveniTextViews.add((TextView) findViewById(R.id.textView9));
        neotkriveniTextViews.add((TextView) findViewById(R.id.textView11));
        neotkriveniTextViews.add((TextView) findViewById(R.id.textView12));
        neotkriveniTextViews.add((TextView) findViewById(R.id.textView13));
        neotkriveniTextViews1.add((TextView) findViewById(R.id.textView14));
        neotkriveniTextViews1.add((TextView) findViewById(R.id.textView18));
        neotkriveniTextViews1.add((TextView) findViewById(R.id.textView35));
        neotkriveniTextViews1.add((TextView) findViewById(R.id.textView19));
        neotkriveniTextViews2.add((TextView) findViewById(R.id.textView36));
        neotkriveniTextViews2.add((TextView) findViewById(R.id.textView37));
        neotkriveniTextViews2.add((TextView) findViewById(R.id.textView38));
        neotkriveniTextViews2.add((TextView) findViewById(R.id.textView39));
        neotkriveniTextViews3.add((TextView) findViewById(R.id.textView40));
        neotkriveniTextViews3.add((TextView) findViewById(R.id.textView42));
        neotkriveniTextViews3.add((TextView) findViewById(R.id.textView43));
        neotkriveniTextViews3.add((TextView) findViewById(R.id.textView41));


        //kolona A
        TextView textView9 = findViewById(R.id.textView9);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView12 = findViewById(R.id.textView12);
        TextView textView13 = findViewById(R.id.textView13);

        //kolona B
        TextView textView14 = findViewById(R.id.textView14);
        TextView textView18 = findViewById(R.id.textView18);
        TextView textView35 = findViewById(R.id.textView35);
        TextView textView19 = findViewById(R.id.textView19);

        //kolona C
        TextView textView36 = findViewById(R.id.textView36);
        TextView textView37 = findViewById(R.id.textView37);
        TextView textView38 = findViewById(R.id.textView38);
        TextView textView39 = findViewById(R.id.textView39);

        //kolona D
        TextView textView40 = findViewById(R.id.textView40);
        TextView textView42 = findViewById(R.id.textView42);
        TextView textView43 = findViewById(R.id.textView43);
        TextView textView41 = findViewById(R.id.textView41);





        EditText editText1 = findViewById(R.id.editTextText);
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String unetiTekst = editText1.getText().toString().trim();
                    if (unetiTekst.equalsIgnoreCase("Partizan")) {
                        editText1.setEnabled(false);
                        editText1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        // Dodaj dva boda za tačan odgovor
                        bodovi += 2;
                        bodovi += 4 - kolonaAotvoreno;
                        //dodajBodoveZaNeotkriveneTextView1();
                        updateBodoviTextView();
                        kolonaAotvoreno = 5;
                    } else {
                        Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        EditText editText18 = findViewById(R.id.editTextText18);
        editText18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String unetiTekst = editText18.getText().toString().trim();
                    if (unetiTekst.equalsIgnoreCase("Kapiten")) {
                        editText18.setEnabled(false);
                        editText18.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        // Dodaj dva boda za tačan odgovor
                        bodovi += 2;
                        bodovi += 4 - kolonaBotvoreno;
                        //dodajBodoveZaNeotkriveneTextView1();
                        updateBodoviTextView();
                        kolonaBotvoreno = 5;
                    } else {
                        Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        EditText editText20 = findViewById(R.id.editTextText20);
        editText20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String unetiTekst = editText20.getText().toString().trim();
                    if (unetiTekst.equalsIgnoreCase("Ubica")) {
                        editText20.setEnabled(false);
                        editText20.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        // Dodaj dva boda za tačan odgovor
                        bodovi += 2;
                        bodovi += 4 - kolonaCotvoreno;

                        //dodajBodoveZaNeotkriveneTextView2();
                        updateBodoviTextView();
                        kolonaCotvoreno = 5;
                    } else {
                        Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        EditText editText22 = findViewById(R.id.editTextText22);
        editText22.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String unetiTekst = editText22.getText().toString().trim();
                    if (unetiTekst.equalsIgnoreCase("Ratnik")) {
                        editText22.setEnabled(false);
                        editText22.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        // Dodaj dva boda za tačan odgovor
                        bodovi += 2;
                        bodovi += 4 - kolonaDotvoreno;

                        //dodajBodoveZaNeotkriveneTextView2();
                        updateBodoviTextView();
                        kolonaDotvoreno = 5;
                    } else {
                        Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        // KOLONA A
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    textView9.setText("Parni valjak");
                    textView9Clicked = true;
                    kolonaAotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();

            }
        });


        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView11Clicked) { //obrisi ifove
                    textView11.setText("Grobari");
                    textView11Clicked = true;
                    kolonaAotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView11.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView12Clicked) {
                    textView12.setText("Košarka");
                    textView12Clicked = true;
                    kolonaAotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView12.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView13Clicked) {
                    textView13.setText("Institucija");
                    textView13Clicked = true;
                    kolonaAotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView13.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });




        textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView14Clicked) {
                    textView14.setText("Vodja tima");
                    textView14Clicked = true;
                    kolonaBotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView14.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView18Clicked) {
                    textView18.setText("Igrac");
                    textView18Clicked = true;
                    kolonaBotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView18.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView35Clicked) {
                    textView35.setText("Odgovornost");
                    textView35Clicked = true;
                    kolonaBotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView35.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView19Clicked) {
                    textView19.setText("Traka");
                    textView19Clicked = true;
                    kolonaBotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView19.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });



        textView36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView36Clicked) {
                    textView36.setText("Moga Oca");
                    textView36Clicked = true;
                    kolonaCotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView36.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });




        textView37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView37Clicked) {
                    textView37.setText("Placeni");
                    textView37Clicked = true;
                    kolonaCotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView37.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });



        textView38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView38Clicked) {
                    textView38.setText("Majk Majers");
                    textView38Clicked = true;
                    kolonaCotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView38.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });



        textView39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView39Clicked) {
                    textView39.setText("Mekog Srca");
                    textView39Clicked = true;
                    kolonaCotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView39.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView40Clicked) {
                    textView40.setText("Vojska");
                    textView40Clicked = true;
                    kolonaDotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView40.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView42Clicked) {
                    textView42.setText("Borba");
                    textView42Clicked = true;
                    kolonaDotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView42.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView43Clicked) {
                    textView43.setText("Trinaesti");
                    textView43Clicked = true;
                    kolonaDotvoreno++;

                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView43.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });


        textView41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textView41Clicked) {
                    textView41.setText("Sparta");
                    textView41Clicked = true;
                    kolonaDotvoreno++;
                    // Dodaj bodove za tačan odgovor


                    updateBodoviTextView();
                } else {
                    String text = textView41.getText().toString();
                    Toast.makeText(Asocijacije.this, text, Toast.LENGTH_SHORT).show();
                }
            }
        });




        EditText editText19 = findViewById(R.id.editTextText19);
        String userInput = editText19.getText().toString();
        editText19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String unetiTekst = editText19.getText().toString().trim();
                    if (unetiTekst.equalsIgnoreCase("Novica Velickovic")) {
                        editText19.setEnabled(false);
                        editText19.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        // Dodaj dva boda za tačan odgovor
                        // Provera i izvršavanje svih EditText-ova
                        /*if (!editText.isEnabled() || !editText.getText().toString().trim().equalsIgnoreCase("Partizan")) {
                            editText.setEnabled(false);
                            editText.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        }

                        if (!editText18.isEnabled() || !editText18.getText().toString().trim().equalsIgnoreCase("Kapiten")) {
                            editText18.setEnabled(false);
                            editText18.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        }

                        if (!editText20.isEnabled() || !editText20.getText().toString().trim().equalsIgnoreCase("Ubica")) {
                            editText20.setEnabled(false);
                            editText20.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        }

                        if (!editText22.isEnabled() || !editText22.getText().toString().trim().equalsIgnoreCase("Ratnik")) {
                            editText22.setEnabled(false);
                            editText22.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                        }*/


                        if(kolonaAotvoreno < 5){
                            bodovi += 4 - kolonaAotvoreno + 2;
                        }
                        if(kolonaBotvoreno < 5){
                            bodovi += 4 - kolonaBotvoreno + 2;
                        }
                        if(kolonaCotvoreno < 5){
                            bodovi += 4 - kolonaCotvoreno + 2;
                        }
                        if(kolonaDotvoreno < 5){
                            bodovi += 4 - kolonaDotvoreno + 2;
                        }


                        bodovi += 7;
                        updateBodoviTextView();
                    } else {
                        Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });





    }

    public void openActivitySkocko() {
        Intent intent = new Intent(this, Skocko.class);
        startActivity(intent);
    }
    private void updateBodoviTextView() {
        bodoviTextView.setText(String.valueOf(bodovi));


    }
}








