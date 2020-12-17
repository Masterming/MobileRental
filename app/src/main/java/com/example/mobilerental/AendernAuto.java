package com.example.mobilerental;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class AendernAuto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aendern_auto);

        Button edit = findViewById(R.id.button);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: code
            }
        });
    }
}