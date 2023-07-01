package com.example.projekat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class Asocijacije extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference asocijacijeCollection;


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();




    private DatabaseReference scoreRef;
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



    public Asocijacije() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asociacije);


        db = FirebaseFirestore.getInstance();
        asocijacijeCollection = db.collection("asocijacije");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        scoreRef = database.getReference("bodovi");



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

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentRef = db.collection("Ascocijacije").document("asocijacije");


        // Dodajte slušaoca za promene na Firebase bazi podataka

        EditText editText1 = findViewById(R.id.editTextText);
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String unetiTekst = editText1.getText().toString().trim();
                    documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String vrednostIzBaze = documentSnapshot.getString("ARes"); // Zamijenite "imePolja" sa stvarnim imenom polja u vašoj bazi
                                editText1.setText(vrednostIzBaze);
                                if (unetiTekst.equalsIgnoreCase(vrednostIzBaze)) {
                                    editText1.setEnabled(false);
                                    editText1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                                    bodovi += 2;
                                    bodovi += 4 - kolonaAotvoreno;
                                    updateBodoviTextView();
                                    kolonaAotvoreno = 5;
                                } else {
                                    Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                        }
                    });
                }
            }
        });


        EditText editText18 = findViewById(R.id.editTextText18);
        editText18.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String unetiTekst = editText18.getText().toString().trim();
                    documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String vrednostIzBaze = documentSnapshot.getString("BRes"); // Zamijenite "imePolja" sa stvarnim imenom polja u vašoj bazi
                                editText18.setText(vrednostIzBaze);
                                if (unetiTekst.equalsIgnoreCase(vrednostIzBaze)) {
                                    editText18.setEnabled(false);
                                    editText18.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                                    bodovi += 2;
                                    bodovi += 4 - kolonaBotvoreno;
                                    updateBodoviTextView();
                                    kolonaBotvoreno = 5;
                                } else {
                                    Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                        }
                    });
                }
            }
        });



        EditText editText20 = findViewById(R.id.editTextText20);
        editText20.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String unetiTekst = editText20.getText().toString().trim();
                    documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String vrednostIzBaze = documentSnapshot.getString("CRes"); // Zamijenite "imePolja" sa stvarnim imenom polja u vašoj bazi
                                editText20.setText(vrednostIzBaze);
                                if (unetiTekst.equalsIgnoreCase(vrednostIzBaze)) {
                                    editText20.setEnabled(false);
                                    editText20.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                                    bodovi += 2;
                                    bodovi += 4 - kolonaCotvoreno;
                                    updateBodoviTextView();
                                    kolonaCotvoreno = 5;
                                } else {
                                    Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                        }
                    });
                }
            }
        });

        EditText editText22 = findViewById(R.id.editTextText22);
        editText22.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String unetiTekst = editText22.getText().toString().trim();
                    documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String vrednostIzBaze = documentSnapshot.getString("DRes"); // Zamijenite "imePolja" sa stvarnim imenom polja u vašoj bazi
                                editText22.setText(vrednostIzBaze);
                                if (unetiTekst.equalsIgnoreCase(vrednostIzBaze)) {
                                    editText22.setEnabled(false);
                                    editText22.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                                    bodovi += 2;
                                    bodovi += 4 - kolonaDotvoreno;
                                    updateBodoviTextView();
                                    kolonaDotvoreno = 5;
                                } else {
                                    Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                        }
                    });
                }
            }
        });
        // KOLONA A

// Dodajte slušatelja za promjene u dokumentu
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("a1");
                            textView9.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("a2");
                            textView11.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });


        textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("a3");
                            textView12.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });


        textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("a4");
                            textView13.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });




        textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("b1");
                            textView14.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("b2");
                            textView18.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });
        textView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("b3");
                            textView35.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("b4");
                            textView19.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("c1");
                            textView36.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });


        textView37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("c2");
                            textView37.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("c3");
                            textView38.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });


        textView39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("c4");
                            textView39.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("d1");
                            textView40.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("d2");
                            textView42.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("d3");
                            textView43.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });

        textView41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String a1 = documentSnapshot.getString("d4");
                            textView41.setText(a1);
                            kolonaAotvoreno++;
                            updateBodoviTextView();
                        } else {
                            Log.d("Ascocijacije", "Dokument 'Ascocijacije' ne postoji");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Ascocijacije", "Greška pri pristupu bazi podataka", e);
                    }
                });
            }
        });


        EditText editText19 = findViewById(R.id.editTextText19);
        String userInput = editText19.getText().toString();
        editText19.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final String unetiTekst = editText19.getText().toString().trim();
                    documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                String vrednostIzBaze = "konacno"; // Zamijenite "ARes" s pravim imenom polja u vašoj bazi

                                editText19.setText(vrednostIzBaze);
                                if (unetiTekst.equalsIgnoreCase(vrednostIzBaze)) {
                                    editText19.setEnabled(false);
                                    editText19.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                                    // Ostatak koda ostaje isti

                                    if (kolonaAotvoreno < 5) {
                                        bodovi += 4 - kolonaAotvoreno + 2;
                                    }
                                    if (kolonaBotvoreno < 5) {
                                        bodovi += 4 - kolonaBotvoreno + 2;
                                    }
                                    if (kolonaCotvoreno < 5) {
                                        bodovi += 4 - kolonaCotvoreno + 2;
                                    }
                                    if (kolonaDotvoreno < 5) {
                                        bodovi += 4 - kolonaDotvoreno + 2;
                                    }

                                    bodovi += 7;
                                    updateBodoviTextView();
                                } else {
                                    Toast.makeText(Asocijacije.this, "Netačan odgovor", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.d("Asocijacije", "Dokument 'Asocijacije' ne postoji");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Asocijacije", "Greška pri pristupu bazi podataka", e);
                        }
                    });
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








