package com.fpt.careermate.services.resume_services.service.impl;

import com.fpt.careermate.services.resume_services.service.dto.request.HighlightProjectRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.HighlightProjectResponse;

public interface HighLightProjectService {
    HighlightProjectResponse addHighlightProjectToResume(HighlightProjectRequest highlightProject);
    void removeHighlightProjectFromResume(int resumeId, int highlightProjectId);
    HighlightProjectResponse updateHighlightProjectInResume(int resumeId,HighlightProjectRequest highlightProject);

}
