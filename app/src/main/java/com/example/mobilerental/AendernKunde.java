package com.example.mobilerental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AendernKunde extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aendern_kunde);

        Button edit = findViewById(R.id.button);
        edit.setOnClickListener(v -> {
            EditText customerID = (EditText)findViewById(R.id.editTextTextPersonName8);
            EditText newFirstName = (EditText)findViewById(R.id.editTextTextPersonName23);
            EditText newLastName = (EditText)findViewById(R.id.editTextTextPersonName22);
            EditText newAddress = (EditText)findViewById(R.id.editTextTextPersonName16);

            boolean bSuccess = false;
            Toast toast;
            try {
                Log.e("EditText", "carID " + customerID.toString() + " newFirstname " + newFirstName.toString() + " newLastName " + newLastName.toString() + " newAddress " + newAddress.toString());
                bSuccess = Rental.editCustomer(Integer.parseInt(customerID.toString()), newFirstName.toString(), newLastName.toString(), newAddress.toString());
            }
            catch(Exception e){
                Log.e("editCustomer failure", e.getMessage());
                bSuccess = false;
            }
            if(bSuccess){
                toast = Toast.makeText(this, "Kunde " + customerID.toString() + " geändert!", Toast.LENGTH_LONG);
            }
            else{
                toast = Toast.makeText(this, "Ändern von Kunde " + customerID.toString() + " fehlgeschlagen.", Toast.LENGTH_LONG);
            }
            toast.show();
        });
    }
}