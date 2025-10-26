package com.fpt.careermate.services.payment_services.repository;

import com.fpt.careermate.services.order_services.domain.Order;
import com.fpt.careermate.services.payment_services.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Integer> {
    Optional<Payment> findByOrder(Order order);
}
