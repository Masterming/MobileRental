package com.example.mobilerental;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper{

    //tables and columns
    public final String TABLE_CARS = "cars";
    public final String TABLE_RENTAL = "rental";
    public final String TABLE_CUSTOMERS = "customers";

    public final String[] CARS_COLUMNS = {"id", "model", "brand", "fuelType", "performance", "type", "seats", "doors", "price"};
    public final String[] RENTAL_COLUMNS ={"id", "startDate", "endDate", "carID", "customerID"};
    public final String[] CUSTOMERS_COLUMNS ={"id", "firstName", "lastName", "address"};

    public DBOpenHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        return;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;
    }
}
