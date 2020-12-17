package com.example.mobilerental;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import com.example.mobilerental.ui.login.LoginActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rental.init(this);


        Button login = findViewById(R.id.button7);
        login.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        });

        Button carselect = findViewById(R.id.button6);
        carselect.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ScrollingActivity.class);
            startActivity(i);
        });

        //test();
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