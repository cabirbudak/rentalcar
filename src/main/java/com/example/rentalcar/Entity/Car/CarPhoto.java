package com.example.rentalcar.Entity.Car;

import jakarta.persistence.*;
import lombok.*;
import com.example.rentalcar.Entity.Car;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carsphoto")

public class CarPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filename")
    private String fileName;

    @ManyToOne
    @JoinColumn(name= "car_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Car car;

    @Column(name = "cover")
    private Boolean cover;
    public CarPhoto(Car car, String fileName, Boolean cover) {
        this.car = car;
        this.fileName = fileName;
        this.cover = cover;
    }
}
