package com.kartikey.docbook.Controller;

import com.kartikey.docbook.model.Role;
import com.kartikey.docbook.model.User;
import com.kartikey.docbook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model, Authentication auth){
        if(auth !=null && auth.isAuthenticated()&& !(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/dashboard";
        }
        model.addAttribute("user",new User());
        model.addAttribute("roles", Role.values());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               @RequestParam(required = false) String specialization,
                               @RequestParam(required = false) String qualification,
                               @RequestParam(required = false) String contactNumber,
                               @RequestParam(required = false) String address){
        userService.registerUser(user,specialization,qualification,contactNumber,address);
        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String showLoginPage(Authentication auth) {
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }
        return "login";
    }


}
