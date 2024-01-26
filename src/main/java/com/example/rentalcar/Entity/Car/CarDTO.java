package com.example.rentalcar.Entity.Car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    @NotBlank
    private String manufacturer;

    @NotBlank
    private String model;

    @NotBlank
    private Integer year;

    @NotBlank
    private String color;

    @NotBlank
    private String plate;

    @NotNull
    private Gearbox gearbox;

    @NotNull
    private CarBody carBody;

    @NotNull
    private Integer price;

    @NotNull
    private EngineType engineType;

    @NotNull
    private Integer power;

    @NotNull
    private WheelDriveType wheelDriveType;

    @NotNull
    private Set<Classification> classifications;

    private MultipartFile cover;

    private MultipartFile[] photos;

}
