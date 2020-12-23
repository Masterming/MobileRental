package com.example.mobilerental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoeschenAuto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loeschen_auto);

        Button del = findViewById(R.id.button);
        del.setOnClickListener(v -> {
            boolean bSuccess = false;
            EditText carID = findViewById(R.id.editTextTextPersonName10);
            try{
                bSuccess = Rental.removeCar(Integer.parseInt(carID.getText().toString()));
            }
            catch(Exception e){
                Log.e("removeCar failure", e.getMessage());
            }
            if(bSuccess){
                Toast.makeText(this, "Auto " + carID.getText() + " erfolgreich entfernt!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Auto " + carID.getText() + " konnte nicht entfernt werden.", Toast.LENGTH_LONG).show();
            }
        });
    }
}