package com.dental.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PatientsController {

    @GetMapping("/patients")
    public String showPatientsPage() {
        return "patients";  // This should match "patients.jsp" in /WEB-INF/views/
    }
}
