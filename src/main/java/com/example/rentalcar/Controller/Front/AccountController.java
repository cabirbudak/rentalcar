package com.example.rentalcar.Controller.Front;


import com.example.rentalcar.Entity.User.PersonalityData;
import com.example.rentalcar.Entity.User.PersonalityDataDTO;
import com.example.rentalcar.Entity.User.Role;
import com.example.rentalcar.Service.OrderService;
import com.example.rentalcar.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AccountController {
        private final UserService userService;
        private final OrderService orderService;

    public AccountController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }
    @GetMapping("/account")
    public String getPersonality(Model model) {
        model.addAttribute(
                "personalityData",
                userService.getCurrentUserPersonalityData().orElse(new PersonalityData()));
        model.addAttribute("ordersList", orderService.getOrdersByUser(userService.getCurrentUser()));
        return "front/account";
    }

    @PostMapping("/account")
    public String updatePersonalityData(@ModelAttribute PersonalityDataDTO personalityDataDTO,
                                        RedirectAttributes redirectAttributes) {
        User user = userService.getCurrentUser();
        if (user == null || userService.updatePersonalityData(user.getId(), personalityDataDTO) == null) {
            redirectAttributes.addFlashAttribute("message", "Произошла ошибка при обновлении");
            return "redirect:account";
        }
        redirectAttributes.addFlashAttribute("message", "Аккаунт обновлен");
        return "redirect:account";
    }
}
