package com.example.rentalcar.Repository;

import com.example.rentalcar.Entity.Car;
import com.example.rentalcar.Entity.Car.CarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarPhotoRepository extends JpaRepository<CarPhoto, Long> {

    Optional<CarPhoto> findByCarAndCover(Car car, Boolean cover);
}
