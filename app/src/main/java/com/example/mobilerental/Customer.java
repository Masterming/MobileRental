package com.example.mobilerental;

public class Customer {
    private int ID;
    private String firstName;
    private String lastName;
    private String address;

    public Customer(int id, String firstName, String lastName, String address) {
        ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
