package com.example.mobilerental;

public class Car {
    private int ID;
    private String model;
    private String brand;
    private String fuelType;
    private String type;
    private int seats;
    private int doors;
    private int performance;
    private int price;
    private boolean booked;

    public Car(int ID, String model, String brand, String fuelType, int performance, String type, int seats, int doors, int price) {
        this.ID = ID;
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.performance = performance;
        this.type = type;
        this.seats = seats;
        this.doors = doors;
        this.price = price;
        this.booked = false;
    }

    public Car(String model, String brand, String fuelType, int performance, String type, int seats, int doors, int price) {
        this.ID = -1;
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.performance = performance;
        this.type = type;
        this.seats = seats;
        this.doors = doors;
        this.price = price;
        this.booked = false;
    }

    public int getID() {
        return ID;
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

    public boolean isBooked() {
        return booked;
    }

    public boolean toggleBooked(){
        booked = !booked;
        return booked;
    }

    @Override
    public String toString() {
        return model + " " + brand;
    }
}
