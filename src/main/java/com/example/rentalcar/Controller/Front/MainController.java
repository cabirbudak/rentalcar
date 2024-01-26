package com.example.rentalcar.Controller.Front;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "front/main";
    }
    @GetMapping("/prices")
    public String prices() {
        return "front/prices";
    }
    @GetMapping("/terms")
    public String terms() {
        return "front/terms";
    }
    @GetMapping("/about")
    public String about() {
        return "front/about";
    }
}
