package com.fpt.careermate.services.authentication_services.domain;

import com.fpt.careermate.services.account_services.domain.Account;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity(name = "forgot_password")
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int fpid;

    @Column(nullable = false)
    int otp;
    @Column(nullable = false)
    Date expiredAt;

    @OneToOne
    Account account;
}
