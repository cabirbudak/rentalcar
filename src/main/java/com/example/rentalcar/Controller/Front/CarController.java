package com.example.rentalcar.Controller.Front;

import com.example.rentalcar.Entity.car;
import com.example.rentalcar.Entity.Car.CarBody;
import com.example.rentalcar.Entity.Car.Classification;
import com.example.rentalcar.Service.CarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping
    public String carsView(@RequestParam(name = "startDate", required = false)
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                           @RequestParam(name = "endDate", required = false)
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                           @RequestParam(name = "classifications", required = false) Classification[] classifications,
                           @RequestParam(name = "carBodies", required = false) CarBody[] carBodies,
                           Model model) {
        List<Car> list;
        if (start == null || end == null) {
            list = carService.findAllCars(classifications, carBodies);
        } else {
            list = carService.findAllFreeCarsByOptions(start, end, classifications, carBodies);
        }
        model.addAttribute("carsList", list);
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);
        model.addAttribute("checkedClassifications", classifications != null ? Arrays.stream(classifications).toList() : null);
        model.addAttribute("checkedCarBodies", carBodies != null ? Arrays.stream(carBodies).toList() : null);
        return "front/cars";
    }
}
