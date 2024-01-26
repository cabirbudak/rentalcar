package com.example.rentalcar.Controller.Admin;

import com.example.rentalcar.Entity.User.PersonalityData;
import com.example.rentalcar.Entity.User.PersonalityDataDTO;
import com.example.rentalcar.Entity.User.Role;
import com.example.rentalcar.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/admin/users")
@EnableMethodSecurity
public class AdminUsersController {

    private final UserService userService;

    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute
    public void addAdminPanelFlag(Model model) {
        model.addAttribute("isAdminPanel", Boolean.TRUE);
    }
    @GetMapping
    public String adminPanelUsersView(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                      @RequestParam(name = "atPage", required = false, defaultValue = "25") Integer atPage,
                                      Model model) {
        model.addAttribute("usersList", userService.getAllUsers(page, atPage));
        return "admin/adminPanelUsers";
    }
    @GetMapping("/update/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String adminPanelUserUpdateView(@PathVariable Long userId, Model model) {
        model.addAttribute(
                "personalityData",
                userService.getPersonalityByUserId(userId).orElse(new PersonalityData()));
        return "admin/adminPanelUserUpdate";
    }
    @PostMapping("/update/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String adminPanelUserUpdate(@PathVariable Long userId,
                                       @ModelAttribute PersonalityDataDTO personalityDataDTO,
                                       @RequestParam Role[] roles,
                                       RedirectAttributes redirectAttributes) {
        if (userService.updatePersonalityData(userId, personalityDataDTO) == null
                || userService.updateRoles(userId, roles) == null) {
            redirectAttributes.addFlashAttribute("message", "An error occurred while updating");
            return "redirect:admin/users/update/" + userId;
        }
        redirectAttributes.addFlashAttribute("message", "Account updated"
        );
        return "redirect:admin/users/update/" + userId;
    }
    @GetMapping("/remove/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String adminPanelUserRemove(@PathVariable Long userId,
                                       RedirectAttributes redirectAttributes) {
        if (userService.setUserActive(userId, false) == null) {
            redirectAttributes.addFlashAttribute("message", "Failed to delete");
        }
        redirectAttributes.addFlashAttribute("message", "User ID=" + userId + "Marked as deleted");
        return "redirect:admin/users";
    }
    @GetMapping("/reduce/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String adminPanelUserReduce(@PathVariable Long userId,
                                       RedirectAttributes redirectAttributes) {
        if (userService.setUserActive(userId, true) == null) {
            redirectAttributes.addFlashAttribute("message", "Failed to restore");
        }
        redirectAttributes.addFlashAttribute("message", "User ID=" + userId + " restored");
        return "redirect:admin/users";
    }
    @PostMapping("/update/setRoles/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String adminPanelUserSetRoles(@PathVariable Long userId,
                                         @RequestParam Role[] roles,
                                         RedirectAttributes redirectAttributes) {
        if (userService.updateRoles(userId, roles) == null) {
            redirectAttributes.addFlashAttribute("message", "An error occurred during update");
        }
        redirectAttributes.addFlashAttribute("message", "Account ID=" + userId + " updated");
        return "redirect:admin/users";
    }
}
