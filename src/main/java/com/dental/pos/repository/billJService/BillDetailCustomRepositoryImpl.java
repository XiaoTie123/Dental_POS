package com.dental.pos.repository.billJService;

import com.dental.pos.entity.BillDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BillDetailCustomRepositoryImpl implements BillDetailCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional // Ensure a transaction is active
    public void updateByDelFlg(Long id) {
        // Correct JPQL update query using parameter binding
        String jpql = "UPDATE BillDetail b SET b.delFlg = 1 WHERE b.bill.billId = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate(); // Execute update inside a transaction
    }


    @Override
    public List<BillDetail> getBillDetailByBillId(Long billId) {
        String jpql = "SELECT bd FROM BillDetail bd WHERE bd.bill.billId = :billId AND bd.delFlg = 0"; // Assumes delFlg = 0 means not deleted
        Query query = entityManager.createQuery(jpql, BillDetail.class); // Specify the entity type in the query
        query.setParameter("billId", billId); // Set the parameter
        return query.getResultList(); // Return the result list
    }
}
