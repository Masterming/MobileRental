package com.example.mobilerental;

public class Car {
    private int ID;
    private boolean booked;
    private String model;
    private String brand;
    private String fuelType;
    private String type;
    private int seats;
    private int doors;
    private int performance;
    private int price;

    public Car(int ID, String model, String brand, String fuelType, String type, int seats, int doors, int performance, int price) {
        this.ID = ID;
        this.booked = false;
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.type = type;
        this.seats = seats;
        this.doors = doors;
        this.performance = performance;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public boolean isBooked() {
        return booked;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public int getDoors() {
        return doors;
    }

    public int getPerformance() {
        return performance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
