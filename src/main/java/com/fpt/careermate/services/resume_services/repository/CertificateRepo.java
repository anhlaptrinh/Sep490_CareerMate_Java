package com.fpt.careermate.services.resume_services.repository;

import com.fpt.careermate.services.resume_services.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertificateRepo extends JpaRepository<Certificate, Integer> {
    @Query("SELECT COUNT(c) FROM certificate c WHERE c.resume.resumeId = :resumeId")
    long countCertificateByResumeId(int resumeId);
}
