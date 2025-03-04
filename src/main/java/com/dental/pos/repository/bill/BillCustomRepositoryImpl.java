package com.dental.pos.repository.bill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class BillCustomRepositoryImpl implements BillCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional // Ensure a transaction is active
    public void updateByDelFlg(Long id) {
        // Correct JPQL update query using parameter binding
        String jpql = "UPDATE Bill b SET b.delFlg = 1 WHERE b.billId = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        query.executeUpdate(); // Execute update inside a transaction
    }
}
