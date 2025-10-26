package com.fpt.careermate.services.resume_services.repository;

import com.fpt.careermate.services.resume_services.domain.HighlightProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighlightProjectRepo extends JpaRepository<HighlightProject, Integer> {
}
