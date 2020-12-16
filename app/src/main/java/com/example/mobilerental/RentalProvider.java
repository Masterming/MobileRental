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
    private SQLiteDatabase db;

    public static final String AUTHORITY = "com.example.mobilerental";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CARS, 1);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CUSTOMERS, 2);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_RENTAL, 3);

        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CARS + "/#", 11);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CUSTOMERS + "/#", 12);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_RENTAL + "/#", 13);
    }

    @Override
    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext(), "database.db");
        db = helper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case 1:
                cursor = db.query(DBOpenHelper.TABLE_CARS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 2:
                cursor = db.query(DBOpenHelper.TABLE_CUSTOMERS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 3:
                cursor = db.query(DBOpenHelper.TABLE_RENTAL, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 11:
                cursor = db.query(DBOpenHelper.TABLE_CARS, projection, selection, selectionArgs, null,
                        "id = " + uri.getFragment(), sortOrder);
                break;
            case 12:
                cursor = db.query(DBOpenHelper.TABLE_CUSTOMERS, projection, selection, selectionArgs, null,
                        "id = " + uri.getFragment(), sortOrder);
                break;
            case 13:
                cursor = db.query(DBOpenHelper.TABLE_RENTAL, projection, selection, selectionArgs, null,
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

        String mime = "vnd.android.cursor.";

        switch (uriMatcher.match(uri)) {
            case 1:
                mime += "dir/vnd.example." + DBOpenHelper.TABLE_CARS;
                break;
            case 2:
                mime += "dir/vnd.example." + DBOpenHelper.TABLE_CUSTOMERS;
                break;
            case 3:
                mime += "dir/vnd.example." + DBOpenHelper.TABLE_RENTAL;
                break;
            case 11:
                mime += "item/vnd.example." + DBOpenHelper.TABLE_CARS;
                break;
            case 12:
                mime += "item/vnd.example." + DBOpenHelper.TABLE_CUSTOMERS;
                break;
            case 13:
                mime += "item/vnd.example." + DBOpenHelper.TABLE_RENTAL;
                break;
            default:
                return null;
        }
        return mime;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if(values == null)
            return null;
        long i;
        Uri ret_uri;
        switch (uriMatcher.match(uri)) {
            case 1:
                 i = db.insert(DBOpenHelper.TABLE_CARS, null, values);
                 ret_uri = Uri.parse("content://" + AUTHORITY + "/" + DBOpenHelper.TABLE_CARS + "/#" + i);
                break;
            case 2:
                i = db.insert(DBOpenHelper.TABLE_CUSTOMERS, null, values);
                ret_uri = Uri.parse("content://" + AUTHORITY + "/" + DBOpenHelper.TABLE_CUSTOMERS + "/#" + i);
                break;
            case 3:
                i = db.insert(DBOpenHelper.TABLE_RENTAL, null, values);
                ret_uri = Uri.parse("content://" + AUTHORITY + "/" + DBOpenHelper.TABLE_RENTAL + "/#" + i);
                break;
            default:
                return null;
        }
        if(i > 0){
            getContext().getContentResolver().notifyChange(ret_uri, null);
            return ret_uri;
        }
        else {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int i;

        switch (uriMatcher.match(uri)) {
            case 1:
                 i = db.delete(DBOpenHelper.TABLE_CARS, selection, selectionArgs);
                break;
            case 2:
                i = db.delete(DBOpenHelper.TABLE_CUSTOMERS, selection, selectionArgs);
                break;
            case 3:
                i = db.delete(DBOpenHelper.TABLE_RENTAL, selection, selectionArgs);
                break;
            case 11:
                i = db.delete(DBOpenHelper.TABLE_CARS, "id = " + uri.getFragment(), null);
                break;
            case 12:
                i = db.delete(DBOpenHelper.TABLE_CUSTOMERS, "id = " + uri.getFragment(), null);
                break;
            case 13:
                i = db.delete(DBOpenHelper.TABLE_RENTAL, "id = " + uri.getFragment(), null);
                break;
            default:
                return 0;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return i;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int i;

        switch (uriMatcher.match(uri)) {
            case 1:
                i = db.update(DBOpenHelper.TABLE_CARS, values,  selection, selectionArgs);
                break;
            case 2:
                i = db.update(DBOpenHelper.TABLE_CUSTOMERS, values, selection, selectionArgs);
                break;
            case 3:
                i = db.update(DBOpenHelper.TABLE_RENTAL, values, selection, selectionArgs);
                break;
            case 11:
                i = db.update(DBOpenHelper.TABLE_CARS, values, "id = " + uri.getFragment(), null);
                break;
            case 12:
                i = db.update(DBOpenHelper.TABLE_CUSTOMERS, values, "id = " + uri.getFragment(), null);
                break;
            case 13:
                i = db.update(DBOpenHelper.TABLE_RENTAL, values, "id = " + uri.getFragment(), null);
                break;
            default:
                return 0;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return i;
    }
}
