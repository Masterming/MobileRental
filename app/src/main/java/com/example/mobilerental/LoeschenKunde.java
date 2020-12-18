package com.example.mobilerental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoeschenKunde extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loeschen_kunde);

        Button del = findViewById(R.id.button);
        del.setOnClickListener(v -> {
            boolean bSuccess = false;
            EditText customerID = findViewById(R.id.editTextTextPersonName10);
            try{
                bSuccess = Rental.removeCustomer(Integer.parseInt(customerID.toString()));
            }
            catch(Exception e){
                Log.e("removeCustomer failure", e.getMessage());
            }
            if(bSuccess){
                Toast.makeText(this, "Kunde " + customerID.toString() + " erfolgreich entfernt!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Kunde " + customerID.toString() + " konnte nicht entfernt werden.", Toast.LENGTH_LONG).show();
            }
        });
    }
}