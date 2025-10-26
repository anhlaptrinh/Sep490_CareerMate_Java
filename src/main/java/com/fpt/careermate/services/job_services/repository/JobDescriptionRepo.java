package com.fpt.careermate.services.job_services.repository;

import com.fpt.careermate.services.job_services.domain.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobDescriptionRepo extends JpaRepository<JobDescription, Integer> {
    List<JobDescription> findByJobPosting_Id(int id);
}
