package com.fpt.careermate.services.resume_services.service.impl;

import com.fpt.careermate.services.resume_services.service.dto.request.AwardRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.AwardResponse;

public interface AwardService {

    AwardResponse addAwardToResume(AwardRequest award);
    void removeAwardFromResume(int resumeId, int awardId);
    AwardResponse updateAwardInResume(int resumeId, int awardId, AwardRequest award);
}
