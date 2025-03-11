package com.dental.pos.controller;

import com.dental.pos.dto.service.ClinicServiceDto;
import com.dental.pos.dto.service.ClinicServiceSearchDto;
import com.dental.pos.entity.ClinicService;
import com.dental.pos.exception.ClinicServiceNotFoundException;
import com.dental.pos.service.ServiceClinicService;
import com.dental.pos.util.common.TextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/clinicService")
public class ClinicServiceController {

    @Autowired
    private ServiceClinicService serviceClinicService;

    @Autowired
    private TextConverter textConverter;

    @GetMapping
    public String listClinicService(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10;
        try {
            Page<ClinicServiceDto> clinicServiceDtoPage = serviceClinicService.getAllClinicService(PageRequest.of(page, pageSize));
            model.addAttribute("clinicServicePage", clinicServiceDtoPage);
        } catch (ClinicServiceNotFoundException e) {
            model.addAttribute("clinicServicePage", Page.empty()); // Empty page instead of error
            model.addAttribute("searchDto", new ClinicServiceSearchDto());
        }
        return "clinicService/clinicServiceList";
    }

    @PostMapping("/search")
    public String searchClinicService(@ModelAttribute("searchDto") ClinicServiceSearchDto searchDto,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {
        int pageSize = 10;

        try {
            Page<ClinicServiceDto> clinicServiceDtoPage = serviceClinicService.searchClinicService(searchDto, PageRequest.of(page, pageSize));
            model.addAttribute("clinicServicePage", clinicServiceDtoPage);
        } catch (ClinicServiceNotFoundException e) {
            model.addAttribute("clinicServicePage", Page.empty()); // Empty page instead of error
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "clinicService/clinicServiceList";
    }

    // Show Create Clinic Service Form
    @GetMapping("/create")
    public String createClinicServiceForm(Model model) {
        model.addAttribute("clinicService", new ClinicServiceDto());
        return "clinicService/clinicServiceCreate";
    }

    // Save Clinic Service
    @PostMapping("/save")
    public String saveClinicService(@ModelAttribute ClinicServiceDto clinicServiceDto) {
        String convertedName = textConverter.convert(clinicServiceDto.getName());
        clinicServiceDto.setName(convertedName);
        serviceClinicService.saveClinicService(clinicServiceDto);
        return "redirect:/clinicService";
    }

    // Show Edit Clinic Service Form
    @GetMapping("/edit/{id}")
    public String editClinicServiceForm(@PathVariable Long id, Model model) {
        Optional<ClinicServiceDto> clinicServiceDto = serviceClinicService.getClinicServiceById(id);
        if (clinicServiceDto.isPresent()) {
            model.addAttribute("clinicService", clinicServiceDto.get());
            return "clinicService/clinicServiceEdit";
        }
        return "redirect:/clinicService";
    }

    @PostMapping("/update/{id}")
    public String updateClinicService(@PathVariable Long id, @ModelAttribute ClinicServiceDto clinicServiceDto) {
        clinicServiceDto.setServiceId(id);
        serviceClinicService.updateClinicService(clinicServiceDto);
        return "redirect:/clinicService";
    }


    // Delete Clinic
    @GetMapping("/delete/{id}")
    public String deleteClinicService(@PathVariable Long id) {
        serviceClinicService.deleteClinicService(id);
        return "redirect:/clinicService";
    }
}