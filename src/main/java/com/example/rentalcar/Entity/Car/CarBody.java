package com.example.rentalcar.Entity.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CarBody {

    CAR_BODY_SEDAN("Sedan"),

    CAR_BODY_HATCHBACK("Hatchback"),

    CAR_BODY_COUPE("Coupe"),

    CAR_BODY_WAGON("Wagon"),

    CAR_BODY_SUV("SUV"),

    CAR_BODY_CROSSOVER("Crossover"),

    CAR_BODY_TRUCK("Truck"),

    CAR_BODY_VAN("Van"),

    CAR_BODY_MINIVAN("MiniVan");

    private final String carBodyType;
}
