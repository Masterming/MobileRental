package com.example.mobilerental;

import java.time.LocalDate;

public class Transaction {
    private int customerID;
    private int carID;
    private LocalDate startDate;
    private int duration; // amount of started days

    public Transaction(int customerID, int carID, LocalDate startDate, int duration) {
        this.customerID = customerID;
        this.carID = carID;
        this.startDate = startDate;
        this.duration = duration;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getCarID() {
        return carID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        if(duration == 0)
            return "Auto " + carID + ": " + "Kunde:" + customerID + ", Datum:" + startDate + ", Dauer: /";
        else
            return "Auto " + carID + ": " + "Kunde: " + customerID + ", Datum: " + startDate + ", Dauer: " + duration;
    }
}
