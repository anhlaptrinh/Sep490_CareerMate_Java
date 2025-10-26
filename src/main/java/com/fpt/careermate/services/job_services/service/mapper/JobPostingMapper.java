package com.fpt.careermate.services.job_services.service.mapper;

import com.fpt.careermate.services.job_services.domain.JobDescription;
import com.fpt.careermate.services.job_services.domain.JobPosting;
import com.fpt.careermate.services.job_services.service.dto.request.JobPostingCreationRequest;
import com.fpt.careermate.services.job_services.service.dto.response.JobPostingForRecruiterResponse;
import com.fpt.careermate.services.job_services.service.dto.response.JobPostingSkillResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface JobPostingMapper {
    JobPosting toJobPosting(JobPostingCreationRequest request);

    List<JobPostingForRecruiterResponse> toJobPostingForRecruiterResponseList(List<JobPosting> jobPostings);
    JobPostingForRecruiterResponse toJobPostingDetailForRecruiterResponse(JobPosting jobPosting);

    Set<JobPostingSkillResponse> toJobPostingSkillResponseSet(Set<JobDescription> jobDescriptions);
}
