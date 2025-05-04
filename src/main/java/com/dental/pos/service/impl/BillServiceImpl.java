package com.dental.pos.service.impl;

import com.dental.pos.dto.bill.BillDetailDto;
import com.dental.pos.dto.bill.BillDto;
import com.dental.pos.dto.bill.BillSearchDto;
import com.dental.pos.entity.Bill;
import com.dental.pos.entity.BillDetail;
import com.dental.pos.entity.ClinicService;
import com.dental.pos.entity.Patient;
import com.dental.pos.exception.BillNotFoundException;
import com.dental.pos.repository.appointment.AppointmentRepository;
import com.dental.pos.repository.bill.BillRepository;
import com.dental.pos.repository.billJService.BillDetailRepository;
import com.dental.pos.repository.clinicService.ClinicServiceRepository;
import com.dental.pos.service.BillService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClinicServiceRepository clinicServiceRepository;

    @Override
    public Page<BillDto> getAllBill(Pageable pageable) {
        Page<Bill> billPage = billRepository.findActiveBill(pageable);
        if (billPage.isEmpty()) {
            throw new BillNotFoundException("No active bill found for the given search criteria.");
        }
        return billPage.map(this::convertToDto);
    }

    @Override
    public Page<BillDto> searchBill(BillSearchDto searchDto, Pageable pageable) {
        Page<Bill> billPage = billRepository.searchBill(searchDto, pageable);

        if (billPage.isEmpty()) {
            throw new BillNotFoundException("No active bill found for the given search criteria.");
        }

        return billPage.map(this::convertToDto);
    }

    private BillDto convertToDto(Bill bill) {
        return new BillDto(bill);
    }

    public BillDto getBillById(Long id) {
        // Retrieve the bill by ID and handle the case where the bill might not be present
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bill with ID " + id + " not found"));

        // Convert the Bill entity to a BillDto
        BillDto billDto = new BillDto(bill);

        // Retrieve related bill details and transform them to BillDetailDto objects
        List<BillDetailDto> billDetailDtoList = billDetailRepository.getBillDetailByBillId(bill.getBillId())
                .stream()
                .map(billDetail -> new BillDetailDto(
                        billDetail.getServiceId().getServiceId(), // Assuming getServiceId() returns a ClinicService with ID
                        billDetail.getServiceName(),
                        (int) Math.round(billDetail.getServiceAmount()),
                        billDetail.getQty()))
                .collect(Collectors.toList());

        // Set the detailed list into the BillDto
        billDto.setBillDetailDtoList(billDetailDtoList);

        return billDto;
    }


    public void saveBill(BillDto billDto) {
        validateBillDto(billDto);

        List<BillDetailDto> detailList = mapServiceDetails(billDto);
        double totalAmount = calculateTotalAmount(detailList);
        double tax = calculateTax(totalAmount, billDto.getTax());
        double percentage = calculatePercentage(totalAmount, billDto.getPercentage());
        double netAmount = calculateNetAmount(totalAmount, tax, percentage, billDto.getPercentage());

        Bill bill = buildBill(billDto, totalAmount, tax, percentage, netAmount);
        billRepository.save(bill);

        saveBillDetails(detailList, bill);
        updateAppointmentStatus(billDto.getPatientId());
    }

    private void validateBillDto(BillDto billDto) {
        if (billDto == null) {
            throw new IllegalArgumentException("BillDto cannot be null");
        }
    }

    private List<BillDetailDto> mapServiceDetails(BillDto billDto) {
        return billDto.getBillDetailDtoList().stream()
                .map(detail -> {
                    ClinicService service = clinicServiceRepository.getById(detail.getServiceId());
                    return new BillDetailDto(service.getServiceId(), service.getName(),
                            (int) Math.round(detail.getServiceAmount()), detail.getQty());
                })
                .collect(Collectors.toList());
    }

    private double calculateTotalAmount(List<BillDetailDto> detailList) {
        return detailList.stream()
                .mapToDouble(detail -> detail.getServiceAmount() * detail.getQty())
                .sum();
    }

    private double calculateTax(double totalAmount, double taxRate) {
        return totalAmount * (taxRate / 100.0);
    }

    private double calculatePercentage(double totalAmount, double percentageRate) {
        return totalAmount * (percentageRate / 100.0);
    }

    private double calculateNetAmount(double totalAmount, double tax, double percentage, int percentageRate) {
        return percentageRate == 100 ? 0 : (totalAmount + tax - percentage);
    }

    private Bill buildBill(BillDto billDto, double totalAmount, double tax, double percentage, double netAmount) {
        return Bill.builder()
                .patient(Patient.builder()
                        .patientId(billDto.getPatientId())
                        .build())
                .doctorId(billDto.getDoctorId())
                .percentage(billDto.getPercentage())
                .tax(billDto.getTax())
                .totalAmount(totalAmount)
                .netAmount(netAmount)
                .transfer(billDto.getTransfer())
                .taxAmount(tax)
                .percentageAmount(percentage)
                .createdTime(new Date())
                .updatedTime(new Date())
                .delFlg(0)
                .build();
    }

    private void saveBillDetails(List<BillDetailDto> detailList, Bill bill) {
        detailList.forEach(detail -> {
            BillDetail billDetail = BillDetail.builder()
                    .bill(Bill.builder()
                            .billId(bill.getBillId())
                            .build())
                    .serviceId(ClinicService.builder()
                            .serviceId(detail.getServiceId())
                            .build())
                    .serviceName(detail.getServiceName())
                    .serviceAmount(detail.getServiceAmount().doubleValue())
                    .totalAmount((detail.getServiceAmount().doubleValue()) * detail.getQty())
                    .qty(detail.getQty())
                    .createdTime(new Date())
                    .updatedTime(new Date())
                    .delFlg(0)
                    .build();
            billDetailRepository.save(billDetail);
        });
    }

    private void updateAppointmentStatus(Long patientId) {
        appointmentRepository.updateByStatus(patientId);
    }

    public void updateBill(BillDto billDto) {
        Bill bill = billRepository.findById(billDto.getBillId())
                .orElseThrow(() -> new BillNotFoundException("Bill not found"));

        Patient resultPatient = new Patient();
        if(billDto.getPatientId() != null){
            resultPatient.setPatientId(billDto.getPatientId());
        }

        bill.setPatient(resultPatient);
        bill.setDoctorId(billDto.getDoctorId());
        bill.setPercentage(billDto.getPercentage());
        bill.setTax(billDto.getTax());
        bill.setTotalAmount(billDto.getTotalAmount().doubleValue());
        bill.setTransfer(billDto.getTransfer());
        bill.setUpdatedTime(new Date());
        billRepository.save(bill);
    }

    public void deleteBill(Long id) {
        billRepository.updateByDelFlg(id);
        billDetailRepository.updateByDelFlg(id);
    }

}
