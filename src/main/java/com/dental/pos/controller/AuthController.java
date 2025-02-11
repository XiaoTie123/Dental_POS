package com.dental.pos.controller;

import com.dental.pos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // This should match the filename "login.jsp" in /WEB-INF/views/
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {
        boolean authenticated = authService.authenticate(email, password);
        if (authenticated) {
            return "redirect:/dashboard"; // Redirect to dashboard after successful login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "redirect:/login?logout";  // Redirect to login after logout
    }
}
