package com.fpt.careermate.services.order_services.repository;

import com.fpt.careermate.services.order_services.domain.CandidateOrder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CandidateOrderRepo extends JpaRepository<CandidateOrder,Integer> {
    Optional<CandidateOrder> findByCandidate_CandidateIdAndIsActiveTrue(int candidateId);

    @Override
    Page<CandidateOrder> findAll(Pageable pageable);
}
