package com.example.mobilerental;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;

public class Bestellung extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestellung);

        updateView();

        Button edit = findViewById(R.id.button);
        edit.setOnClickListener(v -> {
            Rental.bookCar(LocalDate.now());
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }

    private void updateView() {
        TextView name = findViewById(R.id.textView20);
        name.setText(Rental.activeCar.toString());
        String txt;

        TextView performance = findViewById(R.id.textView5);
        txt = Rental.activeCar.getPerformance() + " PS";
        performance.setText(txt);

        TextView type = findViewById(R.id.textView14);
        txt ="Typ: " + Rental.activeCar.getType();
        type.setText(txt);

        TextView seats = findViewById(R.id.textView17);
        txt = Rental.activeCar.getSeats() + " Sitze";
        seats.setText(txt);

        TextView doors = findViewById(R.id.textView18);
        txt = Rental.activeCar.getDoors() + " Türen";
        doors.setText(txt);

        TextView price = findViewById(R.id.textView19);
        txt = Rental.activeCar.getPrice() + "€/Tag";
        price.setText(txt);
    }
}