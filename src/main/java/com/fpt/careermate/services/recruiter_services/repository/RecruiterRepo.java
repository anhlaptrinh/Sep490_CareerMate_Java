package com.fpt.careermate.services.recruiter_services.repository;

import com.fpt.careermate.services.recruiter_services.domain.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepo extends JpaRepository<Recruiter,Integer> {
    Optional<Recruiter> findByAccount_Id(int id);
}
