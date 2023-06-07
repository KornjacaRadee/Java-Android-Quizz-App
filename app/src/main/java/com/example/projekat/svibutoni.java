package com.example.projekat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class svibutoni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.svibutoni);

        Button slagalicabtn = findViewById(R.id.slagalica);
        slagalicabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySlagalica();
            }
        });

        Button koznabtn = findViewById(R.id.kozna);
        koznabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityKozna();
            }
        });

        Button skockobtn = findViewById(R.id.skocko);
        skockobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySkocko();
            }
        });

        Button korakbtn = findViewById(R.id.korak);
        korakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityKorak();
            }
        });

        Button spojnicebtn = findViewById(R.id.spojnice);
        spojnicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySpojnice();
            }
        });
        Button profilcinabtn = findViewById(R.id.profilcic);
        profilcinabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityProfilcina();
            }
        });

        Button asocijacijabtn = findViewById(R.id.asocijacije);
        asocijacijabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAsocijacija();
            }
        });

    }

    public void openActivitySlagalica() {
        Intent intent = new Intent(this, slagalica.class);
        startActivity(intent);
    }

    public void openActivityKozna() {
        Intent intent = new Intent(this, kozna.class);
        startActivity(intent);
    }

    public void openActivitySkocko() {
        Intent intent = new Intent(this, skocko.class);
        startActivity(intent);
    }

    public void openActivityKorak() {
        Intent intent = new Intent(this, korak.class);
        startActivity(intent);
    }

    public void openActivitySpojnice() {
        Intent intent = new Intent(this, korak.class);
        startActivity(intent);
    }

    public void openActivityProfilcina() {
        Intent intent = new Intent(this, profil.class);
        startActivity(intent);
    }

    public void openActivityAsocijacija() {
        Intent intent = new Intent(this, asocijacije.class);
        startActivity(intent);
    }
}



