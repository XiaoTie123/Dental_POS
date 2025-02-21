package com.dental.pos.controller;

import com.dental.pos.dto.patient.PatientDto;
import com.dental.pos.dto.patient.PatientSearchDto;
import com.dental.pos.exception.PatientNotFoundException;
import com.dental.pos.service.PatientService;
import com.dental.pos.util.enums.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientsController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10;
        try {
            Page<PatientDto> patientPage = patientService.getAllPatients(PageRequest.of(page, pageSize));
            model.addAttribute("patientPage", patientPage);
        } catch (PatientNotFoundException e) {
            model.addAttribute("patientPage", Page.empty()); // Empty page instead of error
            model.addAttribute("searchDto", new PatientSearchDto());
        }
        return "patient/patientsList";
    }

    @PostMapping("/search")
    public String searchPatients(@ModelAttribute("searchDto") PatientSearchDto searchDto,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {
        int pageSize = 10;

        try {
            Page<PatientDto> patientPage = patientService.searchPatients(searchDto, PageRequest.of(page, pageSize));
            model.addAttribute("patientPage", patientPage);
        } catch (PatientNotFoundException e) {
            model.addAttribute("patientPage", Page.empty()); // Empty page instead of error
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "patient/patientsList";
    }

    // Show Create Patient Form
    @GetMapping("/create")
    public String createPatientForm(Model model) {
        PatientDto patientDto = new PatientDto();
        patientDto.setRef(patientService.generateNextRefCode());

        model.addAttribute("patient", patientDto);
        model.addAttribute("doctorList", Doctor.getAll());
        return "patient/patientCreate";
    }

    // Save Patient
    @PostMapping("/save")
    public String savePatient(@ModelAttribute PatientDto patientDto) {
        patientService.savePatient(patientDto);
        return "redirect:/patients";
    }

    // Show Edit Patient Form
    @GetMapping("/edit/{id}")
    public String editPatientForm(@PathVariable Long id, Model model) {
        Optional<PatientDto> patientDto = patientService.getPatientById(id);
        if (patientDto.isPresent()) {
            model.addAttribute("patient", patientDto.get());
            model.addAttribute("doctorList", Doctor.getAll());
            return "patient/patientEdit";
        }
        return "redirect:/patients";
    }

    // Update Patient
    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute PatientDto patientDto) {
        patientDto.setPatientId(id);
        patientService.updatePatient(patientDto);
        return "redirect:/patients";
    }


    // Delete Patient
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}