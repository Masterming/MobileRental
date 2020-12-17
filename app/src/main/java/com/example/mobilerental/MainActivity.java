package com.example.mobilerental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.example.mobilerental.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Rental.init(this);
        Button login = findViewById(R.id.button7);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        Button carSelec = findViewById(R.id.button6);
        carSelec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ScrollingActivity.class);
                startActivity(i);
            }
        });

        //test();
    }

    private void test() {
        /*List<Car> cars = Rental.lookup();
        Log.e("Test", "executing test()");
        for (int i = 0; i < cars.size(); i++) {
            Log.e("Test", cars.get(i).toString());
        }

        */
        Rental.login(3);
    }

}