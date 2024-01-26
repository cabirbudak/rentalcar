package com.example.rentalcar.User;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.example.rentalcar.Entity.Car;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "manufacturer")
    private String manufacturer;

    @NotBlank
    @Column(name = "model")
    private String model;

    @NotNull
    @Min(1900)
    @Column(name = "year")
    private Integer year;

    @NotBlank
    @Column(name = "color")
    private String color;

    @NotBlank
    @Column(name = "plate")
    private String plate;

    @NotNull
    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "gearbox")
    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    @NotNull
    @Column(name = "car_body")
    @Enumerated(EnumType.STRING)
    private CarBody carBody;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @NotNull
    @Column(name = "engine")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @NotNull
    @Column(name = "power")
    private Integer power;

    @NotNull
    @Column(name = "wheel_drive")
    @Enumerated(EnumType.STRING)
    private WheelDriveType wheelDriveType;

    @ElementCollection(targetClass = Classification.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "cars_classifications", joinColumns = @JoinColumn(name = "car_id"))
    @JoinTable(
            name = "cars_classifications",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Classification> classifications;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Order> orders;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CarPhoto> photos;

    public void setFromDto(CarDTO carDTO) {
        setManufacturer(carDTO.getManufacturer());
        setModel(carDTO.getModel());
        setYear(carDTO.getYear());
        setColor(carDTO.getColor());
        setPlate(carDTO.getPlate());
        setGearbox(carDTO.getGearbox());
        setPrice(carDTO.getPrice());
        setCarBody(carDTO.getCarBody());
        setClassifications(carDTO.getClassifications());
        setEngineType(carDTO.getEngineType());
        setPower(carDTO.getPower());
        setWheelDriveType(carDTO.getWheelDriveType());
    }
}

