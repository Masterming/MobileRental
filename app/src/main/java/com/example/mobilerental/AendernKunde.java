package com.example.mobilerental;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class AendernKunde extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aendern_kunde);

        Button edit = findViewById(R.id.button);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: code
            }
        });
    }
}