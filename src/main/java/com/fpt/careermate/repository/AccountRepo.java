package com.fpt.careermate.repository;

import com.fpt.careermate.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);
}
