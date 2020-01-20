package com.mesannonces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boutonProfil = (Button) findViewById(R.id.buttonProfil);
        Button boutonVoirAnnonce = (Button) findViewById(R.id.buttonVoirAnnonce);

        boutonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnnonceView.class);
                //intent.putExtra("testText", "Hello world!");

                startActivity(intent);

                Toast toast = Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        boutonVoirAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnnonceView.class);
                //intent.putExtra("testText", "Hello world!");

                startActivity(intent);
            }
        });

    }
}