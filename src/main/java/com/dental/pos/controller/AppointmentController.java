package com.dental.pos.controller;

import com.dental.pos.dto.appointment.AppointmentDto;
import com.dental.pos.dto.appointment.AppointmentSearchDto;
import com.dental.pos.exception.AppointmentNotFoundException;
import com.dental.pos.service.AppointmentService;
import com.dental.pos.service.PatientService;
import com.dental.pos.util.enums.Doctor;
import com.dental.pos.util.enums.Status;
import com.dental.pos.util.enums.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listAppointment(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10;
        try {
            Page<AppointmentDto> appointmentDtoPage = appointmentService.getAllAppointment(PageRequest.of(page, pageSize));
            model.addAttribute("appointmentPage", appointmentDtoPage);
            model.addAttribute("doctorList", Doctor.getAll());
        } catch (AppointmentNotFoundException e) {
            model.addAttribute("appointmentPage", Page.empty()); // Empty page instead of error
            model.addAttribute("doctorList", Doctor.getAll());
            model.addAttribute("searchDto", new AppointmentSearchDto());
        }
        return "appointment/appointmentList";
    }

    @PostMapping("/search")
    public String searchAppointment(@ModelAttribute("searchDto") AppointmentSearchDto searchDto,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {
        int pageSize = 10;

        try {
            Page<AppointmentDto> appointmentDtoPage = appointmentService.searchAppointment(searchDto, PageRequest.of(page, pageSize));
            model.addAttribute("appointmentPage", appointmentDtoPage);
            model.addAttribute("doctorList", Doctor.getAll());
        } catch (AppointmentNotFoundException e) {
            model.addAttribute("appointmentPage", Page.empty()); // Empty page instead of error
            model.addAttribute("doctorList", Doctor.getAll());
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "appointment/appointmentList";
    }

    // Show Create Appointment Form
    @GetMapping("/create")
    public String createAppointmentForm(Model model) {
        model.addAttribute("appointment", new AppointmentDto());
        model.addAttribute("patientList", patientService.getPatientList());
        model.addAttribute("doctorList", Doctor.getAll());
        model.addAttribute("timeList", Time.getAll());
        model.addAttribute("statusList", Status.getAll());
        return "appointment/appointmentCreate";
    }

    // Save Appointment
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute AppointmentDto appointmentDto) {
        appointmentService.saveAppointment(appointmentDto);
        return "redirect:/appointment";
    }

    // Show Edit Appointment Form
    @GetMapping("/edit/{id}")
    public String editAppointmentForm(@PathVariable Long id, Model model) {
        Optional<AppointmentDto> appointmentDto = appointmentService.getAppointmentById(id);
        if (appointmentDto.isPresent()) {
            model.addAttribute("appointment", appointmentDto.get());
            model.addAttribute("statusList", Status.getAll());
            model.addAttribute("patientList", patientService.getPatientList());
            model.addAttribute("doctorList", Doctor.getAll());
            model.addAttribute("timeList", Time.getAll());
            return "appointment/appointmentEdit";
        }
        return "redirect:/appointment";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute AppointmentDto appointmentDto) {
        appointmentDto.setAppointmentId(id);
        appointmentService.updateAppointment(appointmentDto);
        return "redirect:/appointment";
    }


    // Delete Appointment
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointment";
    }
}