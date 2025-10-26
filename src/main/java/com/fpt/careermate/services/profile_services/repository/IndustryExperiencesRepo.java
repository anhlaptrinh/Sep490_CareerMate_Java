package com.fpt.careermate.services.profile_services.repository;

import com.fpt.careermate.services.profile_services.domain.IndustryExperienceId;
import com.fpt.careermate.services.profile_services.domain.IndustryExperiences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryExperiencesRepo extends JpaRepository<IndustryExperiences, IndustryExperienceId> {
}
