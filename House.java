package com.example.projectgui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class House implements Serializable {
    private static final long serialVersionUID = 1L;

    private int houseNumber;
    private HouseStatus status;
    private Person tenant;
    private double rentAmount;
    private boolean rentPaid;

    public House(int houseNumber, HouseStatus status, double rentAmount) {
        this.houseNumber = houseNumber;
        this.status = status;
        this.tenant = null;
        this.rentAmount = rentAmount;
        this.rentPaid = false;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public HouseStatus getStatus() {
        return status;
    }

    public Person getTenant() {
        return tenant;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public boolean isRentPaid() {
        return rentPaid;
    }




    public void occupyHouse(Person tenant) {
        if (status == HouseStatus.FOR_RENT && this.tenant == null) {
            this.tenant = tenant;
            status = HouseStatus.OCCUPIED;
            System.out.println("House " + houseNumber + " allocated to " + tenant.getName());
        } else {
            System.out.println("House " + houseNumber + " is not available for allocation.");
        }
    }

    public void vacateHouse() {
        if (status == HouseStatus.OCCUPIED) {
            tenant = null;
            status = HouseStatus.VACANT;
            rentPaid = false;
            System.out.println("House " + houseNumber + " vacated.");
        } else {
            System.out.println("House " + houseNumber + " is not occupied.");
        }
    }

    public void payRent() {
        if (status == HouseStatus.OCCUPIED && !rentPaid) {
            System.out.println("Rent collected for House " + houseNumber + " from " + tenant.getName());
            rentPaid = true;
        } else {
            System.out.println("House " + houseNumber + " is not available for rent collection or rent is already paid.");
        }
    }

    public abstract void displayDetails();

    public void resetHouse() {
        this.tenant = null;
        this.status = HouseStatus.FOR_RENT;
    }


}
