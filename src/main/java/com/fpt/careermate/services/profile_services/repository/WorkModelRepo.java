package com.fpt.careermate.services.profile_services.repository;

import com.fpt.careermate.services.profile_services.domain.WorkModel;
import com.fpt.careermate.services.profile_services.domain.WorkModelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkModelRepo extends JpaRepository<WorkModel, WorkModelId> {
    void deleteByCandidateId(Integer candidateId);
}
