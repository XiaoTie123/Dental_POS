package com.dental.pos.repository.billJService;

import com.dental.pos.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long>, BillDetailCustomRepository {

}
