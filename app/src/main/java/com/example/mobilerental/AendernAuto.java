package com.example.mobilerental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AendernAuto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aendern_auto);

        Button edit = findViewById(R.id.button);
        edit.setOnClickListener(v -> {
            EditText carID = (EditText)findViewById(R.id.editTextTextPersonName10);
            EditText newPrice = (EditText)findViewById(R.id.editTextTextPersonName7);

            boolean bSuccess = false;
            Toast toast;
            try {
                Log.e("EditText", "carID " + carID.getText().toString() + " newPrice " + newPrice.getText().toString());
                bSuccess = Rental.editCar(Integer.parseInt(carID.getText().toString()), Integer.parseInt(newPrice.getText().toString()));
            }
            catch(Exception e){
                Log.e("editCar failure", e.getMessage());
                bSuccess = false;
            }
            if(bSuccess){
                toast = Toast.makeText(this, "Auto " + carID.getText() + " geändert!", Toast.LENGTH_LONG);
            }
            else{
                toast = Toast.makeText(this, "Ändern von Auto " + carID.getText() + " fehlgeschlagen.", Toast.LENGTH_LONG);
            }
            toast.show();
        });
    }
}