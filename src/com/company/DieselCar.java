package com.company;

public class DieselCar extends Car{

    protected DieselCar(int maximumRange, CarBrand brand){
        super(maximumRange, brand);
    }

    @Override
    public void drive() {
        System.out.println("Wroom!");
    }

}
