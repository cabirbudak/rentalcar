package com.example.rentalcar.Controller.Admin;

import com.example.rentalcar.Entity.Car;
import com.example.rentalcar.Entity.car.CarDTO;
import com.example.rentalcar.Service.CarService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Arrays;

@Controller
@RequestMapping
@EnableMethodSecurity
public class AdminCarsController {

    private final CarService carService;

    public AdminCarsController(CarService carService) { this.carService = carService; }

    @ModelAttribute
    public void addAdminPanelFlag(Model model) {
        model.addAttribute("isAdminPanel", Boolean.TRUE);
    }

    @GetMapping
    public String adminPanelCarsView(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam(name = "atPage", required = false, defaultValue = "25") Integer atPage,
                                     Model model) {
        model.addAttribute("carsList", carService.findAllCarsPage(page, atPage));
        return "admin/adminPanelCars";
    }
    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelAddCarView() {
        return "admin/adminPanelCarAdd";
    }
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelAddCar(@ModelAttribute CarDTO carDTO,
                                   RedirectAttributes redirectAttributes) {
        Car car = carService.addCar(carDTO);
        if (car == null) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "A car with this license plate already exists");
            return "redirect:admin/cars/add";
        }
        return "redirect:admin/cars/update/" + car.getId();
    }
    @GetMapping("/update/{carId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelUpdateCarView(@PathVariable Long carId, Model model) {
        model.addAttribute(
                "car",
                carService.findCarById(carId));
        return "admin/adminPanelCarUpdate";
    }
    @PostMapping("/update/{carId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelUpdateCar(@ModelAttribute CarDTO carDTO,
                                      @PathVariable Long carId,
                                      RedirectAttributes redirectAttributes) {
        if (carService.updateCarById(carId, carDTO) == null) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "An error occurred while updating");
            return "redirect:admin/cars/update/" + carId;
        }
        redirectAttributes.addFlashAttribute(
                "message",
                "The car has been updated");
        return "redirect:admin/cars/update/" + carId;
    }
    @GetMapping("/remove/{carId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelCarRemove(@PathVariable Long carId,
                                      RedirectAttributes redirectAttributes) {
        if (carService.setCarActive(carId, false) == null) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Delete failed");
        }
        redirectAttributes.addFlashAttribute(
                "message",
                "Car ID =" + carId + " marked as deleted");
        return "redirect:admin/cars";
    }
    @GetMapping("/reduce/{carId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelCarReduce(@PathVariable Long carId,
                                      RedirectAttributes redirectAttributes) {
        if (carService.setCarActive(carId, true) == null) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Failed to restore");
        }
        redirectAttributes.addFlashAttribute(
                "message",
                "Vehicle ID=" + carId + "Restored");
        return "redirect:admin/cars";
    }
    @PostMapping("/update/photo/delete/{carId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelCarPhotoDelete(@PathVariable Long carId,
                                           @RequestParam Long[] deletePhotoId,
                                           RedirectAttributes redirectAttributes) {
        carService.carPhotoDelete(deletePhotoId);
        redirectAttributes.addFlashAttribute(
                "message",
                "Photo ID=" + Arrays.toString(deletePhotoId) + " уделены");

        return "redirect:admin/cars/update/" + carId;
    }
}

