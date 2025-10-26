package com.fpt.careermate.services.authentication_services.repository;

import com.fpt.careermate.services.authentication_services.domain.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidDateTokenRepo extends JpaRepository<InvalidToken, String> {
}
