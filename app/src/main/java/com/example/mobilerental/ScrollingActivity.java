package com.example.mobilerental;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private ViewGroup linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        linearLayout = findViewById(R.id.linearLayout);
        updateView();
    }

    private void updateView(){
        List<Car> cars = Rental.lookup();
        for(Car c: cars){
            createButton(c.toString(), c);
        }
    }

    private void createButton(String text, Car c) {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        Button b = new Button(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 10, 0, 0);
        b.setLayoutParams(layoutParams);
        b.setText(text);
        b.setOnClickListener(v -> {
            Rental.activeCar = c;
            Intent i = new Intent(getApplicationContext(), Bestellung.class);
            startActivity(i);
        });
        this.linearLayout.addView(b);
    }
}