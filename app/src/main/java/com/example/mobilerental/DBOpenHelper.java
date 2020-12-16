package com.example.mobilerental;

import androidx.annotation.Nullable;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.*;
import java.io.*;

public class DBOpenHelper extends SQLiteOpenHelper {

    private final Context mContext;

    // tables and columns
    public final String TABLE_CARS = "cars";
    public final String TABLE_RENTAL = "rental";
    public final String TABLE_CUSTOMERS = "customers";

    public final String[] CARS_COLUMNS = { "id", "model", "brand", "fuelType", "performance", "type", "seats", "doors", "price" };
    public final String[] RENTAL_COLUMNS = { "id", "startDate", "endDate", "carID", "customerID" };
    public final String[] CUSTOMERS_COLUMNS = { "id", "firstName", "lastName", "address" };

    public DBOpenHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO: save database.sql to resources
        String file = "database.sql";
        AssetManager am = mContext.getAssets();

        try {
            InputStream is = am.open(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            // TODO: test script execution
            while ((line = reader.readLine()) != null)
            {
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
