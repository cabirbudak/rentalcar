package com.example.rentalcar.Controller.Admin;


import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@EnableMethodSecurity
public class AdminController {

    @ModelAttribute
    public void addAdminPanelFlag(Model model) {
        model.addAttribute("isAdminPanel", Boolean.TRUE);
    }

    @GetMapping
    public String adminPanelView() {
        return "admin/adminPanel";
    }
}
