package com.fpt.careermate.services.authentication_services.repository;

import com.fpt.careermate.services.account_services.domain.Account;
import com.fpt.careermate.services.authentication_services.domain.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepo extends JpaRepository<ForgotPassword, Integer> {
    @Query("select fp from forgot_password fp where fp.otp = ?1 and fp.account = ?2 ")
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, Account account);
}
