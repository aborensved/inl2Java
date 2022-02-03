package com.company;

import java.util.Locale;
import java.util.NoSuchElementException;

public class CarFactory {

    public Car createCar(String CarType){
        return switch(CarType.toLowerCase()){
            case "tesla" -> new ElectricCar(450, CarBrand.TESLA);
            case "bmw" -> new DieselCar(800, CarBrand.BMW);
            case "volvo" -> new DieselCar(700, CarBrand.VOLVO);
            case "volkswagen" -> new DieselCar(750, CarBrand.VOLKSWAGEN);
            default -> throw new NoSuchElementException("Unfortunately, the car you want doesn't exists");
        };
    }

}
