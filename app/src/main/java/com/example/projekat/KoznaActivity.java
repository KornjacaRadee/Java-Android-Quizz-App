package com.example.projekat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class KoznaActivity extends AppCompatActivity {
    private Button button24;
    private Button button25;
    private Button button26;
    private Button button16;
    private TextView textView22;
    private EditText scoreEditText;

    private FirebaseFirestore db;
    private DocumentReference documentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kozna);

        button24 = findViewById(R.id.button24);
        button25 = findViewById(R.id.button25);
        button26 = findViewById(R.id.button26);
        button16 = findViewById(R.id.button16);
        textView22 = findViewById(R.id.textView22);
        scoreEditText = findViewById(R.id.score);

        db = FirebaseFirestore.getInstance();
        documentRef = db.collection("Kozna").document("kozna");

        // Dohvatanje podataka iz Firebase baze
        documentRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Dohvaćanje vrednosti polja iz dokumenta
                                String odgA = document.getString("odgA");
                                String odgB = document.getString("odgB");
                                String odgC = document.getString("odgC");
                                String odgD = document.getString("odgD");
                                String konacno = document.getString("pitanje");

                                // Postavljanje teksta na dugmad
                                button16.setText(odgA);
                                button25.setText(odgB);
                                button24.setText(odgC);
                                button26.setText(odgD);

                                textView22.setText(konacno);
                            } else {
                                Log.d("FirebaseData", "Dokument ne postoji");
                            }
                        } else {
                            Log.e("FirebaseData", "Greška prilikom dohvatanja dokumenta", task.getException());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("FirebaseData", "Greška prilikom dohvatanja dokumenta", e);
                    }
                });

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    // Dodavanje 10 bodova
                    String newScore =  "10";

                    // Postavljanje nove vrednosti bodova u EditText score
                    scoreEditText.setText(String.valueOf(newScore));

                    Toast.makeText(KoznaActivity.this, "Tacan Odgovor", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Log.e("Button16Click", "Greška pri pretvaranju vrednosti bodova", e);
                }
            }
        });
    }
}
