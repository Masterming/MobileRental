package com.example.mobilerental;

import androidx.annotation.Nullable;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.*;
import java.io.*;

public class DBOpenHelper extends SQLiteOpenHelper {

    private final Context mContext;

    // tables and columns
    public static final String TABLE_CARS = "cars";
    public static final String TABLE_RENTAL = "rental";
    public static final String TABLE_CUSTOMERS = "customers";

    public static final String[] CARS_COLUMNS = { "id", "model", "brand", "fuelType", "performance", "type", "seats", "doors", "price" , "booked"};
    public static final String[] RENTAL_COLUMNS = { "id", "startDate", "endDate", "carID", "customerID" };
    public static final String[] CUSTOMERS_COLUMNS = { "id", "firstName", "lastName", "address" };

    public DBOpenHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String file = "raw/database.sql";
        AssetManager am = mContext.getAssets();

        try {
            InputStream is = am.open(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            // TODO: test script execution
            while (reader.ready())
            {
                line = reader.readLine();
                db.execSQL(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
