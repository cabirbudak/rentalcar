package com.example.rentalcar.Controller.Admin;

import com.example.rentalcar.Service.OrderService;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/orders")
@EnableMethodSecurity
public class AdminOrdersController {

    private final OrderService orderService;

    public AdminOrdersController(OrderService orderService) {
        this.orderService = orderService;
    }
    @ModelAttribute
    public void addAdminPanelFlag(Model model) {
        model.addAttribute("isAdminPanel", Boolean.TRUE);
    }
    @GetMapping
    public String adminPanelOrders(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                   @RequestParam(name = "atPage", required = false, defaultValue = "25") Integer atPage,
                                   Model model) {
        model.addAttribute("ordersList", orderService.getAllOrders(page, atPage));
        return "admin/adminPanelOrders";
    }
}
