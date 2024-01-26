package com.example.rentalcar.Entity.Car;


import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.rentalcar.Entity.Car;

@Getter
@AllArgsConstructor
public enum EngineType {
    ENGINE_PETROL("Petrol"),
    ENGINE_DIESEL("Diesel"),
    ENGINE_ELECTRIC("Electric");

    private final String engineType;

}
