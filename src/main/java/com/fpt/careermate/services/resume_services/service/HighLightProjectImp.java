package com.fpt.careermate.services.resume_services.service;

import com.fpt.careermate.services.resume_services.service.dto.request.HighlightProjectRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.HighlightProjectResponse;
import com.fpt.careermate.services.resume_services.service.impl.HighLightProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HighLightProjectImp implements HighLightProjectService {
    @Override
    public HighlightProjectResponse addHighlightProjectToResume(HighlightProjectRequest highlightProject) {
        return null;
    }

    @Override
    public void removeHighlightProjectFromResume(int resumeId, int highlightProjectId) {

    }

    @Override
    public HighlightProjectResponse updateHighlightProjectInResume(int resumeId, HighlightProjectRequest highlightProject) {
        return null;
    }
}
