package com.company;

public class ElectricCar extends Car{

    protected ElectricCar(int maximumRange, CarBrand brand){
        super(maximumRange, brand);
    }

    @Override
    public void drive() {
        System.out.println("quuuuuuiiiieeet");
    }
}
