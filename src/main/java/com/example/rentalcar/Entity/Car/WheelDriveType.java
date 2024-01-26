package com.example.rentalcar.Entity.Car;


import com.example.rentalcar.Entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WheelDriveType {
    ALL_WHEEL_DRIVE("WHOLE"),
    FRONT_WHEEL_DRIVE("FRONT"),
    REAR_WHEEL_DRIVE("REAR");

    private final String wheelDrive;
}
