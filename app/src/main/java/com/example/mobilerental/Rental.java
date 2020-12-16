package com.example.mobilerental;

import java.util.ArrayList;
import java.util.List;

public class Rental {
    private static Customer activeCustomer;
    private static List<Car> cars = new ArrayList<Car>();

    private Rental(int customerID) {} // cannot be instantiated

    // looks up available cars in db
    public static List<Car> lookup() {
        return null; // lookup in db
    }

    public static Car getCar(int carID) {
        return null; // not yet implemented
    }

    // books the car carID for user customerID
    public static boolean bookCar(int customerID, int carID) {
        return false; // not yet implemented
    }

    // returns car
    public static boolean returnCar(int carID) {
        return false; // not yet implemented
    }

    private static boolean login(int customerID) {
        activeCustomer = null; // dbConn.getCustomer
        return activeCustomer != null;
    }

    public static List<Transaction> getTransactions() {
        return null; // not yet implemented
    }

    public static boolean removeCustomer(int customerID) {
        return false; // not yet implemented
    }

    public static boolean removeCar(int carID) {
        return false; // not yet implemented
    }

    public static boolean editCustomer(int customerID, String editedFirstName, String editedLastName, String editedAddress) {
        // edit, then write back to db
        return false; // not yet implemented
    }

    public static boolean editCar(int carID, int editedPrice) {
        // edit, then write back to db
        return false; // not yet implemented
    }

    public static boolean addCustomer(Customer customer) {
        return false; // not yet implemented
    }

    public static boolean addCar(Car car) {
        return false; // not yet implemented
    }

}
