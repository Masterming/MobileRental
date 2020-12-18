package com.example.mobilerental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnlegenKunde extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anlegen_kunde);

        Button create = findViewById(R.id.button);
        create.setOnClickListener(v -> {
            EditText firstName = findViewById(R.id.editTextTextPersonName3);
            EditText lastName = findViewById(R.id.editTextTextPersonName2);
            EditText address = findViewById(R.id.editTextTextPersonName);

            boolean bSuccess = false;
            try{
                bSuccess = Rental.addCustomer(new Customer(firstName.toString(), lastName.toString(), address.toString()));
            }
            catch(Exception e){
                Log.e("addCustomer", e.getMessage());
                bSuccess = false;
            }
            if(bSuccess){
                Toast.makeText(this, firstName.toString() + " " + lastName.toString() + " wurde erfolgreich angelegt!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Kunde konnte nicht angelegt werden.", Toast.LENGTH_LONG).show();

            }
        });
    }
}