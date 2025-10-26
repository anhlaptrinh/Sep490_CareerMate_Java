package com.fpt.careermate.services.resume_services.service.impl;

import com.fpt.careermate.services.resume_services.service.dto.request.SkillRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.SkillResponse;

public interface SkillService {
    SkillResponse addSkillToResume(SkillRequest skill);
    void removeSkillFromResume(int resumeId, int skillId);
    SkillResponse updateSkillInResume(int resumeId,int skillId, SkillRequest skill);

}
