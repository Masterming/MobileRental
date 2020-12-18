package com.example.mobilerental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Rental {
    private static Customer activeCustomer;
    public static Car activeCar;
    private static final List<Car> cars = new ArrayList<>();
    private static RentalProvider provider;
    private static int rentalID = -1;
    private static int carID = -1;
    private static int customerID = -1;

    private Rental() {} // cannot be instantiated

    public static void init(Context context){
        if(provider == null)
            provider = new RentalProvider(context);
    }

    // looks up available cars in db
    public static List<Car> lookup() {
        Cursor cursor = provider.query(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS), null, "booked = 0", null, null);
        if(cursor != null){
            while (cursor.moveToNext()) {
                Car c = new Car(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)
                        , cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8));
                cars.add(c);
            }
            cursor.close();
        }
        else
            Log.e("SQL", "Connection failed");
        return cars;
    }

    // looks up booked cars in db
    public static List<Car> getBookedCars() {
        Cursor cursor = provider.query(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS), null, "booked = 1", null, null);
        List<Car> bookedCars = new ArrayList<>();
        if(cursor != null){
            while (cursor.moveToNext()) {
                Car c = new Car(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4)
                        , cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8));
                bookedCars.add(c);
            }
            cursor.close();
        }
        else
            Log.e("SQL", "Connection failed");
        return bookedCars;
    }

    //returns an unbooked car with id carID from cache
    public static Car getCar(int carID) {
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getID() == carID)
                return cars.get(i);
        }
        return null;
    }

    //gets the customer with ID customerID from the database
    public static Customer getCustomer(int customerID){
        Cursor cursor = provider.query(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CUSTOMERS + "/" + customerID), null, null, null, null);
        if(cursor != null){
            if(cursor.moveToNext()) {
                Log.e("Rental", cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
                Customer c = new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                cursor.close();
                return c;
            }
        }
        else
            Log.e("Rental", "Query failed, Cursor is null");
        return null;
    }

    // books the car carID for user customerID
    public static boolean bookCar(LocalDate start) {
        ContentValues cv = new ContentValues(1);
        cv.put("booked", 1);
        Uri uri = Uri.fromParts("content", RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS + "/", Integer.toString(carID));
        int i = provider.update(uri, cv, null, null);
        if(i == 0){
            Log.e("SQL", "Connection failed");
            return false;
        }

        if(rentalID == -1){
            Cursor cursor = provider.query(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_RENTAL), new String[]{"id"}, null, null, "DESC");
            if(cursor != null){
                if(cursor.moveToFirst())
                    rentalID = cursor.getInt(0);
                else rentalID = 0;
                cursor.close();
            }
        }

        cv = new ContentValues(4);
        cv.put("id", ++rentalID);
        cv.put("startDate", start.toString());
        cv.put("carID", activeCar.getID());
        cv.put("customerID", activeCustomer.getID());
        uri = provider.insert(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_RENTAL), cv);
        if(uri != null)
            lookup();
        return uri != null;
    }

    // returns car carID to the "unbooked" state
    public static boolean returnCar(int carID, LocalDate end) {
        ContentValues cv = new ContentValues(1);
        cv.put("booked", 0);
        int i = provider.update(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS + "/" + carID), cv, null, null);
        if(i == 0)
            return false;

        cv = new ContentValues(4);
        cv.put("endDate", end.toString());
        i = provider.update(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_RENTAL), cv, null, null);
        if(i != 0)
            lookup();
        return i != 0;
    }

    public static Customer login(int customerID) {
        activeCustomer = getCustomer(customerID);
        return activeCustomer;
    }

    public static void logout(){
        activeCustomer = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<Transaction> getTransactions() {
        List<Transaction> retList = new ArrayList<>();
        Cursor cursor = provider.query(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_RENTAL), null, null,  null, " id DESC");
        if(cursor != null) {
            while (cursor.moveToNext()) {
                LocalDate start = LocalDate.parse(cursor.getString(1));
                if(cursor.isNull(2)){
                    retList.add(new Transaction(cursor.getInt(3), cursor.getInt(4), start, 0));
                }
                else{
                    LocalDate end = LocalDate.parse(cursor.getString(2));
                    int duration = start.until(end).getDays();
                    retList.add(new Transaction(cursor.getInt(3), cursor.getInt(4), start, duration));
                }
            }
            cursor.close();
        }
        else
            Log.e("SQL", "Connection failed");
        return retList;
    }

    public static boolean removeCustomer(int customerID) {
        if(customerID == activeCustomer.getID())
            return false; //cannot delete active user
        int i = provider.delete(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CUSTOMERS + "/" + customerID), null, null);
        return i != 0;
    }

    public static boolean removeCar(int carID) {
        int i = provider.delete(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS + "/" + carID), null, null);
        if(i != 0)
            lookup();
        return i != 0;
    }

    public static boolean editCustomer(int customerID, String editedFirstName, String editedLastName, String editedAddress) {
        ContentValues cv = new ContentValues(3);
        cv.put("firstName", editedFirstName);
        cv.put("lastName", editedLastName);
        cv.put("address", editedAddress);
        int i = provider.update(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CUSTOMERS + "/" + customerID), cv, null, null);
        return i != 0;
    }

    public static boolean editCar(int carID, int editedPrice) {
        ContentValues cv = new ContentValues(1);
        cv.put("price", editedPrice);
        int i = provider.update(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS + "/" + carID), cv, null, null);
        if(i != 0)
            lookup();
        return i != 0;
    }

    public static boolean addCustomer(Customer customer) {
        if(customerID == -1){
            Cursor cursor = provider.query(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CUSTOMERS), new String[]{"id"}, null, null, "DESC");
            if (cursor != null) {
                if(cursor.moveToFirst()){
                    customerID = cursor.getInt(0);
                }
                else customerID = 0;
                cursor.close();
            }
            else
                Log.e("SQL", "Connection failed");
        }

        ContentValues cv = new ContentValues(4);
        cv.put("id", ++customerID);
        cv.put("firstName", customer.getFirstName());
        cv.put("lastName", customer.getLastName());
        cv.put("address", customer.getAddress());
        Uri uri = provider.insert(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CUSTOMERS), cv);
        return uri != null;
    }

    public static boolean addCar(Car car) {
        if(carID == -1){
            Cursor cursor = provider.query(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS), new String[]{"id"}, null, null, "DESC");
            if(cursor != null) {
                if (cursor.moveToFirst()) {
                    carID = cursor.getInt(0);
                } else carID = 0;
                cursor.close();
            }
            else
                Log.e("SQL", "Connection failed");
        }

        ContentValues cv = new ContentValues(9);
        cv.put("id", ++carID);
        cv.put("model", car.getModel());
        cv.put("brand", car.getBrand());
        cv.put("fuelType", car.getFuelType());
        cv.put("type", car.getType());
        cv.put("seats", car.getSeats());
        cv.put("doors", car.getDoors());
        cv.put("performance", car.getPerformance());
        cv.put("price", car.getPrice());

        Uri uri = provider.insert(Uri.parse("content://" + RentalProvider.AUTHORITY + "/" + DBOpenHelper.TABLE_CARS), cv);
        if(uri != null)
            lookup();
        return uri != null;
    }

    public static boolean getActive() {
        return activeCustomer != null;
    }
}
