package com.dental.pos.repository.billJService;

import com.dental.pos.entity.BillDetail;

import java.util.List;

public interface BillDetailCustomRepository {

    void updateByDelFlg(Long id);

    List<BillDetail> getBillDetailByBillId(Long billId);
}