package com.example.mobilerental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import com.example.mobilerental.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public Button carSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rental.init(this);

        Button login = findViewById(R.id.button1);
        login.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        });

        Button logout = findViewById(R.id.button2);
        logout.setOnClickListener(v -> {
            Rental.logout();
            carSel.setEnabled(false);
            Toast.makeText(this, "Ausgeloggt", Toast.LENGTH_LONG).show();
        });

        carSel = findViewById(R.id.button3);
        carSel.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ScrollingActivity.class);
            startActivity(i);
        });

        Button management = findViewById(R.id.button4);
        management.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Management.class);
            startActivity(i);
        });

        //test();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carSel.setEnabled(Rental.getActive());
    }

    private void test() {
        List<Car> cars = Rental.lookup();
        Log.e("Test", "executing test()");
        for (int i = 0; i < cars.size(); i++) {
            Log.e("Test", cars.get(i).toString());
        }
        Rental.login(3);
    }
}