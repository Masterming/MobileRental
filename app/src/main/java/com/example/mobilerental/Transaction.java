package com.example.mobilerental;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private int customerID;
    private int carID;
    private LocalDate startDate;
    private int duration; //duration in full days

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
}
