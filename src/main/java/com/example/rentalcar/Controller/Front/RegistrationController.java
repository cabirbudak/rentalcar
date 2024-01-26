package com.example.rentalcar.Controller.Front;

import jakarta.validation.Valid;
import com.example.rentalcar.Entity.User;
import com.example.rentalcar.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/registration")
    public String showRegistration () {
        return "front/registration";
    }
    @PostMapping("/registration")
    public String registerUser(@Valid User user,
                               @RequestParam String confirmedPassword,
                               Model model) {
        return UserService.register(user, confirmedPassword, model);
    }
}
