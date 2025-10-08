package com.fpt.careermate.repository;

import com.fpt.careermate.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobPostingRepo extends JpaRepository<JobPosting, Integer> {
    List<JobPosting> findAllByStatus(String status);
}
