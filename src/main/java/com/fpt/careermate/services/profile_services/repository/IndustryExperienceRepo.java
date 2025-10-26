package com.fpt.careermate.services.profile_services.repository;

import com.fpt.careermate.services.profile_services.domain.IndustryExperiences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryExperienceRepo extends JpaRepository<IndustryExperiences, Integer> {
    void deleteByCandidateId(Integer candidateId);
}
