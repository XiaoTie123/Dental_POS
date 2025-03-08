package com.dental.pos.controller;

import com.dental.pos.service.AuthService;
import com.dental.pos.util.enums.Doctor;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
                        Model model, HttpSession session) {
        boolean authenticated = authService.authenticate(email, password);
        if (authenticated) {
            session.setAttribute("user", email); // Set user email or any user identifier in session
            return "redirect:/dashboard"; // Redirect to dashboard after successful login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("doctorList", Doctor.getAll()); // Empty page instead of error
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/login?logout";  // Redirect to login after logout
    }
}
