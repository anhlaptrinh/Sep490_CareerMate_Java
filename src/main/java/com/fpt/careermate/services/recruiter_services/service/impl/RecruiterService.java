package com.fpt.careermate.services.recruiter_services.service.impl;


import com.fpt.careermate.services.recruiter_services.service.dto.request.RecruiterCreationRequest;
import com.fpt.careermate.services.recruiter_services.service.dto.response.NewRecruiterResponse;
import com.fpt.careermate.services.recruiter_services.service.dto.response.RecruiterApprovalResponse;

import java.util.List;

public interface RecruiterService {
    NewRecruiterResponse createRecruiter(RecruiterCreationRequest request);

    // Admin approval methods
    List<RecruiterApprovalResponse> getPendingRecruiters();
    RecruiterApprovalResponse approveRecruiter(int recruiterId);
    void rejectRecruiter(int recruiterId, String reason);
    List<RecruiterApprovalResponse> getAllRecruiters();
}
