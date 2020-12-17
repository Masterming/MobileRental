package com.example.mobilerental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

public class AnlegenKunde extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anlegen_kunde);

        Button create = findViewById(R.id.button);
        create.setOnClickListener(v -> {
            // TODO: code
        });
    }
}