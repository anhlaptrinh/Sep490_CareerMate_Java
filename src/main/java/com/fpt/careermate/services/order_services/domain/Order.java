package com.fpt.careermate.services.order_services.domain;

import com.fpt.careermate.services.payment_services.domain.Payment;
import com.fpt.careermate.services.profile_services.domain.Candidate;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, unique = true)
    String orderCode;
    Long amount;
    String status;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate createAt;
    LocalDate cancelledAt;

    String packageNameSnapshot;
    Long packagePriceSnapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id")
    Package candidatePackage;

    @OneToMany(mappedBy = "order")
    List<Payment> payments;
}
