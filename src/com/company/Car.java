package com.company;

public abstract class Car {

    private int maximumRange;
    private CarBrand brand;

    protected Car(int maximumRange, CarBrand brand){
        this.maximumRange = maximumRange;
        this.brand = brand;
    }



    public int getMaximumRange() {
        return maximumRange;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public abstract void drive();
}
