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
                Log.e("EditText", "carID " + customerID.getText() + " newFirstname " + newFirstName.getText() + " newLastName " + newLastName.getText() + " newAddress " + newAddress.getText());
                bSuccess = Rental.editCustomer(Integer.parseInt(customerID.getText().toString()), newFirstName.getText().toString(), newLastName.getText().toString(), newAddress.getText().toString());
            }
            catch(Exception e){
                Log.e("editCustomer failure", e.getMessage());
                bSuccess = false;
            }
            if(bSuccess){
                toast = Toast.makeText(this, "Kunde " + customerID.getText() + " geändert!", Toast.LENGTH_LONG);
            }
            else{
                toast = Toast.makeText(this, "Ändern von Kunde " + customerID.getText() + " fehlgeschlagen.", Toast.LENGTH_LONG);
            }
            toast.show();
        });
    }
}