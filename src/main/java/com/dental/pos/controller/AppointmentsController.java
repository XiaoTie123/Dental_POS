package com.dental.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

    @GetMapping
    public String showAppointmentsPage() {
        return "appointments";  // Matches "appointments.jsp" in /WEB-INF/views/
    }
}