package com.example.mobilerental;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RentalProvider extends ContentProvider {

    private SQLiteDatabase db;
    private boolean init = false;
    private Context context;

    public static final String AUTHORITY = "com.example.mobilerental";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public RentalProvider(){}

    public RentalProvider(Context context) {
        this.context = context;
    }

    static {
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CARS, 1);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CUSTOMERS, 2);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_RENTAL, 3);

        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CARS + "/#", 11);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_CUSTOMERS + "/#", 12);
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.TABLE_RENTAL + "/#", 13);
    }

    public void createDB(){
        if(!init){
            init = true;
            DBOpenHelper helper = new DBOpenHelper(context, "database.db");
            db = helper.getWritableDatabase();
        }
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
            @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        createDB();
        Cursor cursor;
        Log.e("RentalProvider", Uri.decode(uri.toString()));
        switch (uriMatcher.match(uri)) {
            case 1:
                cursor = db.query(DBOpenHelper.TABLE_CARS, projection, selection, selectionArgs, null, null, sortOrder);
                Log.e("RentalProvider", "case 1");
                break;
            case 2:
                cursor = db.query(DBOpenHelper.TABLE_CUSTOMERS, projection, selection, selectionArgs, null, null,
                        sortOrder);
                Log.e("RentalProvider", "case 2");
                break;
            case 3:
                cursor = db.query(DBOpenHelper.TABLE_RENTAL, projection, selection, selectionArgs, null, null,
                        sortOrder);
                Log.e("RentalProvider", "case 3");
                break;
            case 11:
                cursor = db.query(DBOpenHelper.TABLE_CARS, projection, "id = " + uri.getPathSegments().get(1), selectionArgs, null,
                        null, sortOrder);
                Log.e("RentalProvider", "case 11");
                break;
            case 12:
                cursor = db.query(DBOpenHelper.TABLE_CUSTOMERS, projection, "id = " + uri.getPathSegments().get(1), selectionArgs, null,
                        null, sortOrder);
                Log.e("RentalProvider", "case 12");
                break;
            case 13:
                cursor = db.query(DBOpenHelper.TABLE_RENTAL, projection, "id = " + uri.getPathSegments().get(1), selectionArgs, null,
                        null, sortOrder);
                Log.e("RentalProvider", "case 13");
                break;
            default:
                Log.e("RentalProvider", "case DEFAULT");
                return null;
        }

        cursor.setNotificationUri(context.getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        createDB();
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
        createDB();
        if (values == null)
            return null;
        long i;
        Uri ret_uri;
        switch (uriMatcher.match(uri)) {
            case 1:
                i = db.insert(DBOpenHelper.TABLE_CARS, null, values);
                ret_uri = Uri.parse("content://" + AUTHORITY + "/" + DBOpenHelper.TABLE_CARS + "/#" + i);
                Log.e("RentalProvider", "insert case 1");
                break;
            case 2:
                i = db.insert(DBOpenHelper.TABLE_CUSTOMERS, null, values);
                ret_uri = Uri.parse("content://" + AUTHORITY + "/" + DBOpenHelper.TABLE_CUSTOMERS + "/#" + i);
                Log.e("RentalProvider", "insert case 2");
                break;
            case 3:
                i = db.insert(DBOpenHelper.TABLE_RENTAL, null, values);
                ret_uri = Uri.parse("content://" + AUTHORITY + "/" + DBOpenHelper.TABLE_RENTAL + "/#" + i);
                Log.e("RentalProvider", "insert case 3");
                break;
            default:
                return null;
        }
        if (i > 0) {
            context.getContentResolver().notifyChange(ret_uri, null);
            return ret_uri;
        } else {
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        createDB();
        int i;

        switch (uriMatcher.match(uri)) {
            case 1:
                i = db.delete(DBOpenHelper.TABLE_CARS, selection, selectionArgs);
                Log.e("RentalProvider", "delete case 1");
                break;
            case 2:
                i = db.delete(DBOpenHelper.TABLE_CUSTOMERS, selection, selectionArgs);
                Log.e("RentalProvider", "delete case 2");
                break;
            case 3:
                i = db.delete(DBOpenHelper.TABLE_RENTAL, selection, selectionArgs);
                Log.e("RentalProvider", "delete case 3");
                break;
            case 11:
                i = db.delete(DBOpenHelper.TABLE_CARS, "id = " + uri.getPathSegments().get(1), null);
                Log.e("RentalProvider", "delete case 11");
                break;
            case 12:
                i = db.delete(DBOpenHelper.TABLE_CUSTOMERS, "id = " + uri.getPathSegments().get(1), null);
                Log.e("RentalProvider", "delete case 12");
                break;
            case 13:
                i = db.delete(DBOpenHelper.TABLE_RENTAL, "id = " + uri.getPathSegments().get(1), null);
                Log.e("RentalProvider", "delete case 13");
                break;
            default:
                return 0;
        }
        context.getContentResolver().notifyChange(uri, null);
        return i;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
            @Nullable String[] selectionArgs) {
        createDB();
        int i;

        switch (uriMatcher.match(uri)) {
            case 1:
                i = db.update(DBOpenHelper.TABLE_CARS, values, selection, selectionArgs);
                Log.e("RentalProvider", "update case 1");
                break;
            case 2:
                i = db.update(DBOpenHelper.TABLE_CUSTOMERS, values, selection, selectionArgs);
                Log.e("RentalProvider", "update case 2");
                break;
            case 3:
                i = db.update(DBOpenHelper.TABLE_RENTAL, values, selection, selectionArgs);
                Log.e("RentalProvider", "update case 3");
                break;
            case 11:
                i = db.update(DBOpenHelper.TABLE_CARS, values, "id = " + uri.getPathSegments().get(1), null);
                Log.e("RentalProvider", "update case 11");
                break;
            case 12:
                i = db.update(DBOpenHelper.TABLE_CUSTOMERS, values, "id = " + uri.getPathSegments().get(1), null);
                Log.e("RentalProvider", "update case 12");
                break;
            case 13:
                i = db.update(DBOpenHelper.TABLE_RENTAL, values, "id = " + uri.getPathSegments().get(1), null);
                Log.e("RentalProvider", "update case 13");
                break;
            default:
                return 0;
        }
        context.getContentResolver().notifyChange(uri, null);
        return i;
    }
}
