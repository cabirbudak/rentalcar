package com.example.rentalcar.Controller.Front;


import com.example.rentalcar.Entity.Order;
import com.example.rentalcar.Service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping
public class OrderController {

    private final OrderService orderService

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    @PreAuthorize("hasRole('APPROVED_CLIENT')")
    public String orderAdd(@RequestParam Long carId,
                           @RequestParam(name = "startDate", required = false)
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                           @RequestParam(name = "endDate", required = false)
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                           RedirectAttributes redirectAttributes) {
        Order order = orderService.addOrder(carId, start, end);
        if (order != null) {
            redirectAttributes.addFlashAttribute("orderCreated", true);
            return "redirect:account#order_" + order.getId();
        }
        redirectAttributes.addFlashAttribute("orderCreated", false);
        return "redirect:account";
    }
    @GetMapping("/cancel/{orderId}")
    @PreAuthorize("hasAnyRole('APPROVED_CLIENT', 'MANAGER', 'ADMIN')")
    public String orderCancelView(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.getOrderById(orderId).orElse(new Order()));
        return "front/orderCancel";
    }
    @PostMapping("/cancel/{orderId}")
    @PreAuthorize("hasAnyRole('APPROVED_CLIENT', 'MANAGER', 'ADMIN')")
    public String orderCancel(@PathVariable Long orderId, RedirectAttributes redirectAttributes) {
        if (orderService.cancelOrder(orderId) != null) {
            redirectAttributes.addFlashAttribute("message", "Заказ №" + orderId + " отменен успешно.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Заказ №" + orderId + " отменить не удалось");
        }
        return "redirect:account#order_" + orderId;
    }
}
