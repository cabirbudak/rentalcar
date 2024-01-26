package com.example.rentalcar.Entity.Car;


import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.rentalcar.Entity.Car;

@Getter
@AllArgsConstructor
public enum GearBox {
    AUTO("AUTO"),
    MECHANIC("MANUAL");
    private final String gearboxType;
}
