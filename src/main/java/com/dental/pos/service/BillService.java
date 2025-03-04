package com.dental.pos.service;

import com.dental.pos.dto.bill.BillDto;
import com.dental.pos.dto.bill.BillSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BillService {

    Page<BillDto> getAllBill(Pageable pageable);

    Page<BillDto> searchBill(BillSearchDto searchDto, Pageable pageable);

    BillDto getBillById(Long id);

    void saveBill(BillDto billDto);

    void deleteBill(Long id);
}
