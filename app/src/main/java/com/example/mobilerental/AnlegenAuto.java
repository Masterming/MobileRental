package com.example.mobilerental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnlegenAuto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anlegen_auto);

        Button create = findViewById(R.id.button);
        create.setOnClickListener(v -> {
            boolean bSuccess = false;
            EditText model = findViewById(R.id.editTextTextPersonName5);
            EditText brand = findViewById(R.id.editTextTextPersonName6);
            EditText fuelType = findViewById(R.id.editTextTextPersonName13);
            EditText type = findViewById(R.id.editTextTextPersonName15);
            EditText seats = findViewById(R.id.editTextTextPersonName4);
            EditText doors = findViewById(R.id.editTextTextPersonName12);
            EditText performance = findViewById(R.id.editTextTextPersonName14);
            EditText price = findViewById(R.id.editTextTextPersonName11);
            try {
                bSuccess = Rental.addCar(new Car(model.getText().toString(), brand.getText().toString(), fuelType.getText().toString(), Integer.parseInt(performance.getText().toString()),
                        type.getText().toString(), Integer.parseInt(seats.getText().toString()), Integer.parseInt(doors.getText().toString()),  Integer.parseInt(price.getText().toString())));
            }
            catch(Exception e){
                Log.e("addCar failure", e.getMessage());
                bSuccess = false;
            }
            if(bSuccess){
                Toast.makeText(this, brand.getText() + " " + model.getText() + " wurde erfolgreich angelegt!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Auto konnte nicht angelegt werden.", Toast.LENGTH_LONG).show();
            }
        });
    }
}