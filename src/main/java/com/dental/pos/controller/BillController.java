package com.dental.pos.controller;

import com.dental.pos.dto.bill.BillDetailDto;
import com.dental.pos.dto.bill.BillDto;
import com.dental.pos.dto.bill.BillSearchDto;
import com.dental.pos.exception.BillNotFoundException;
import com.dental.pos.service.BillService;
import com.dental.pos.service.PatientService;
import com.dental.pos.service.ServiceClinicService;
import com.dental.pos.util.enums.Doctor;
import com.dental.pos.util.enums.Transfer;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/bill")
public class BillController {

    private final ServletContext servletContext;

    public BillController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Autowired
    private BillService billService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ServiceClinicService serviceClinicService;

    @GetMapping
    public String listBill(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10;
        try {
            Page<BillDto> billDtoPage = billService.getAllBill(PageRequest.of(page, pageSize));
            model.addAttribute("billPage", billDtoPage);
            model.addAttribute("doctorList", Doctor.getAll());
        } catch (BillNotFoundException e) {
            model.addAttribute("billPage", Page.empty()); // Empty page instead of error
            model.addAttribute("doctorList", Doctor.getAll());
            model.addAttribute("searchDto", new BillSearchDto());
        }
        return "bill/billList";
    }

    @PostMapping("/search")
    public String searchBill(@ModelAttribute("searchDto") BillSearchDto searchDto,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model) {
        int pageSize = 10;

        try {
            Page<BillDto> billDtoPage = billService.searchBill(searchDto, PageRequest.of(page, pageSize));
            model.addAttribute("billPage", billDtoPage);
            model.addAttribute("doctorList", Doctor.getAll());
        } catch (BillNotFoundException e) {
            model.addAttribute("billPage", Page.empty()); // Empty page instead of error
            model.addAttribute("doctorList", Doctor.getAll());
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "bill/billList";
    }

    // Show Create Bill Form
    @GetMapping("/create")
    public String createBillForm(Model model) {
        model.addAttribute("bill", new BillDto());
        model.addAttribute("patientList", patientService.getPatientList());
        model.addAttribute("doctorList", Doctor.getAll());
        model.addAttribute("transferList", Transfer.getAll());
        model.addAttribute("clinicServiceList", serviceClinicService.getClinicServiceList());
        return "bill/billCreate";
    }

    @PostMapping("/addServiceLine")
    public String addServiceLine(@ModelAttribute("bill") BillDto bill, Model model) {
        bill.getBillDetailDtoList().add(new BillDetailDto()); // Add a new empty service line
        model.addAttribute("bill", bill);
        model.addAttribute("patientList", patientService.getPatientList());
        model.addAttribute("doctorList", Doctor.getAll());
        model.addAttribute("transferList", Transfer.getAll());
        model.addAttribute("clinicServiceList", serviceClinicService.getClinicServiceList());
        return "bill/billCreate";
    }

    @PostMapping("/removeServiceLine/{index}")
    public String removeServiceLine(@PathVariable int index, @ModelAttribute("bill") BillDto bill, Model model) {
        if (bill.getBillDetailDtoList() != null && bill.getBillDetailDtoList().size() > index) {
            bill.getBillDetailDtoList().remove(index);
        }
        model.addAttribute("bill", bill);
        model.addAttribute("patientList", patientService.getPatientList());
        model.addAttribute("doctorList", Doctor.getAll());
        model.addAttribute("transferList", Transfer.getAll());
        model.addAttribute("clinicServiceList", serviceClinicService.getClinicServiceList());
        return "bill/billCreate";
    }

    // Save Bill
    @PostMapping("/save")
    public String saveBill(@ModelAttribute BillDto billDto) {
        billService.saveBill(billDto);
        return "redirect:/bill";
    }

    // Show Check Bill Form
    @GetMapping("/check/{id}")
    public String editBillForm(@PathVariable Long id, Model model) {
        BillDto billDto = billService.getBillById(id);
        if (billDto != null) {
            model.addAttribute("bill", billDto);
            model.addAttribute("patientList", patientService.getPatientList());
            model.addAttribute("doctorList", Doctor.getAll());
            model.addAttribute("transfer", Transfer.getAll());
            return "bill/billCheck";
        }
        return "redirect:/bill";
    }

    // Delete Bill
    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return "redirect:/bill";
    }

    @GetMapping("/paySlip/{id}")
    public ResponseEntity<byte[]> paySlip(@PathVariable Long id) throws IOException {
        BillDto billDto = billService.getBillById(id);

        Resource resource = new ClassPathResource("static/images/pay_slip.jpeg");
        BufferedImage image = ImageIO.read(resource.getInputStream());
        Graphics2D graphics = image.createGraphics();

        // Customize your font and color
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        graphics.setColor(Color.BLACK);


        for(int i = 0; billDto.getBillDetailDtoList().size() > i; i++){
            if(i == 0){
                graphics.drawString("1", 90, 705);
                graphics.drawString(billDto.getBillDetailDtoList().get(i).getServiceName(), 170, 705);
                graphics.drawString(billDto.getBillDetailDtoList().get(i).getQty() + "", 625, 705);
                graphics.drawString(billDto.getBillDetailDtoList().get(i).getServiceAmountDesc(), 700, 705);
            }
        }
        graphics.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", baos);
        byte[] imageInByte = baos.toByteArray();

        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String filename = billDto.getPatientName() + "_" + dateTime + ".jpeg"; // Append formatted date time to the filename

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                .body(imageInByte);
    }
}