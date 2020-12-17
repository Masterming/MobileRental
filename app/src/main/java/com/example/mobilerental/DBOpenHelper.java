package com.example.mobilerental;

import androidx.annotation.Nullable;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.*;
import android.util.Log;

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
        Log.d("DBOpenHelper", "execute onCreate()");
        String file = "database.sql";
        assert mContext != null;
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
                Log.d("DBOpenHelper", line);
            }
        } catch (IOException e) {
            Log.d("DBOpenHelper: Exception", e.getMessage());
        }
        //String script = "CREATE TABLE cars (id int NOT NULL PRIMARY KEY, model char(50), brand char(50), fuelType char(50), performance int, type char(50), seats int, doors int, price int, booked int DEFAULT(0));";
        //db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
