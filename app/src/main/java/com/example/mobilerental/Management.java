package com.example.mobilerental;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;

public class Management extends AppCompatActivity {

    LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        linearLayout = findViewById(R.id.linearLayout);
        updateView();

        Button simulate = findViewById(R.id.button);
        simulate.setOnClickListener(v -> {
            simulateDay();
            updateView();
            Toast.makeText(this, "Simulation läuft", Toast.LENGTH_LONG);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void simulateDay(){
        List<Car> cars = Rental.getBookedCars();

        for(Car c: cars){
            Log.e("Simulation", "Return car" + c.getID());
            if(Math.random() < 0.2){
                Rental.returnCar(c.getID(), LocalDate.now());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateView(){
        List<Transaction> transactions = Rental.getTransactions();
        for(Transaction t: transactions){
            createText(t.toString());
        }
    }

    private void createText(String text){
        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 10, 0, 0);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        this.linearLayout.addView(tv);
    }
}