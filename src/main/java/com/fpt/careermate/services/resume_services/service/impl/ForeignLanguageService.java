package com.fpt.careermate.services.resume_services.service.impl;

import com.fpt.careermate.services.resume_services.service.dto.request.ForeignLanguageRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.ForeignLanguageResponse;

public interface ForeignLanguageService {
    ForeignLanguageResponse addForeignLanguageToResume(ForeignLanguageRequest foreignLanguage);
    void removeForeignLanguageFromResume(int resumeId, int foreignLanguageId);
    ForeignLanguageResponse updateForeignLanguageInResume(int resumeId, int foreignLanguageId, ForeignLanguageRequest foreignLanguage);

}
