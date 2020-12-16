package com.example.mobilerental;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RentalProvider extends ContentProvider {
    private static DBOpenHelper helper;
    private SQLiteDatabase db;

    private static final String AUTHORITY = "com.example.mobilerental";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, helper.TABLE_CARS, 1);
        uriMatcher.addURI(AUTHORITY, helper.TABLE_CUSTOMERS, 2);
        uriMatcher.addURI(AUTHORITY, helper.TABLE_RENTAL, 3);

        uriMatcher.addURI(AUTHORITY, helper.TABLE_CARS + "/#", 11);
        uriMatcher.addURI(AUTHORITY, helper.TABLE_CUSTOMERS + "/#", 12);
        uriMatcher.addURI(AUTHORITY, helper.TABLE_RENTAL + "/#", 13);
    }

    @Override
    public boolean onCreate() {
        helper = new DBOpenHelper(getContext(), "database.db");
        db = helper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case 1:
                cursor = db.query(helper.TABLE_CARS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 2:
                cursor = db.query(helper.TABLE_CUSTOMERS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 3:
                cursor = db.query(helper.TABLE_RENTAL, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 11:
                cursor = db.query(helper.TABLE_CARS, projection, selection, selectionArgs, null,
                        "id = " + uri.getFragment(), sortOrder);
                break;
            case 12:
                cursor = db.query(helper.TABLE_CUSTOMERS, projection, selection, selectionArgs, null,
                        "id = " + uri.getFragment(), sortOrder);
                break;
            case 13:
                cursor = db.query(helper.TABLE_RENTAL, projection, selection, selectionArgs, null,
                        "id = " + uri.getFragment(), sortOrder);
                break;
            default:
                return null;
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
