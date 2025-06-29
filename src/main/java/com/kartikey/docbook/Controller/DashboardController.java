package com.kartikey.docbook.Controller;

import com.kartikey.docbook.model.Role;
import com.kartikey.docbook.model.User;
import com.kartikey.docbook.repositories.UserRepo;
import com.kartikey.docbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final UserRepo userRepo;

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails){
        User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow();
        Role role = user.getRole();

        return switch (role) {
            case DOCTOR -> "redirect:/doctor/dashboard";
            case PATIENT -> "redirect:/patient/dashboard";
            default -> "redirect:/login?error=unauthorized";
        };
    }
}
