package com.dental.pos.repository.bill;

import com.dental.pos.dto.bill.BillSearchDto;
import com.dental.pos.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long>, BillCustomRepository {

    @Query("SELECT b FROM Bill b WHERE b.delFlg = 0 " +
            "AND (:#{#searchDto.patientName} IS NULL OR LOWER(b.patient.name) LIKE LOWER(CONCAT('%', :#{#searchDto.patientName}, '%'))) " +
            "AND (:#{#searchDto.patientPhone} IS NULL OR b.patient.phone LIKE CONCAT('%', :#{#searchDto.patientPhone}, '%')) " +
            "AND (:#{#searchDto.doctorId} IS NULL OR b.doctorId = :#{#searchDto.doctorId}) order by b.billId desc")
    Page<Bill> searchBill(BillSearchDto searchDto, Pageable pageable);


    @Query("SELECT b FROM Bill b WHERE b.delFlg = 0 order by b.billId desc")
    Page<Bill> findActiveBill(Pageable pageable);
}
