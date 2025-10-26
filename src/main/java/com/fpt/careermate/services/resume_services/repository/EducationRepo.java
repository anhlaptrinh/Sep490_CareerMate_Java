package com.fpt.careermate.services.resume_services.repository;

import com.fpt.careermate.services.resume_services.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EducationRepo extends JpaRepository<Education, Integer> {
    @Query("SELECT COUNT(e) FROM education e WHERE e.resume.resumeId = :resumeId")
    int countEducationByResumeId(@Param("resumeId") int resumeId);

}
