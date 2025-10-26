package com.fpt.careermate.services.resume_services.service.impl;

import com.fpt.careermate.services.resume_services.service.dto.request.EducationRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.EducationResponse;

public interface EducationService {
    EducationResponse addEducationToResume( EducationRequest education);
    void removeEducationFromResume( int educationId);
    EducationResponse updateEducationInResume(int resumeId,int educationId, EducationRequest education);

}
