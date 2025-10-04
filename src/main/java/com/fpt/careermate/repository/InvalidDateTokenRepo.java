package com.fpt.careermate.repository;

import com.fpt.careermate.domain.InvalidDateToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidDateTokenRepo extends JpaRepository<InvalidDateToken, String> {
}
