package com.example.rentalcar.Entity.Car;


import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.rentalcar.Entity.Car;

@Getter
@AllArgsConstructor
public enum Classification {
    CLASS_ECONOMY("Economy"),
    CLASS_COMFORT("Comfort"),
    CLASS_BUSINESS("Business"),
    CLASS_SUV("SUV"),
    CLASS_PREMIUM("Premium"),
    CLASS_SPORT("Sport");
    private final String classificationType;
}

