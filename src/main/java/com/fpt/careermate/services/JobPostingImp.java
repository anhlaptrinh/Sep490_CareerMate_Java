package com.fpt.careermate.services;

import com.fpt.careermate.constant.StatusJobPosting;
import com.fpt.careermate.domain.Account;
import com.fpt.careermate.domain.JobPosting;
import com.fpt.careermate.domain.Recruiter;
import com.fpt.careermate.repository.JobPostingRepo;
import com.fpt.careermate.repository.RecruiterRepo;
import com.fpt.careermate.services.dto.request.JobPostingCreationRequest;
import com.fpt.careermate.services.dto.response.JobPostingResponse;
import com.fpt.careermate.services.impl.JobPostingService;
import com.fpt.careermate.services.mapper.JobPostingMapper;
import com.fpt.careermate.web.exception.AppException;
import com.fpt.careermate.web.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class JobPostingImp implements JobPostingService {

    JobPostingRepo jobPostingRepo;
    RecruiterRepo recruiterRepo;
    JobPostingMapper jobPostingMapper;
    AuthenticationImp authenticationImp;

//    @PreAuthorize("hasRole('RECRUITER')")
    @Override
    public void createJobPosting(JobPostingCreationRequest request) {
        // Check expiration date
        if (!request.getExpirationDate().isAfter(LocalDate.now())) throw new AppException(ErrorCode.INVALID_EXPIRATION_DATE);

        Account currentAccount = authenticationImp.findByEmail();
        Optional<Recruiter> currentRecruiter = recruiterRepo.findByAccount_Id(currentAccount.getId());
        Recruiter recruiter = currentRecruiter.get();

        JobPosting jobPosting = jobPostingMapper.toJobPosting(request);
        jobPosting.setCreateAt(LocalDate.now());
        jobPosting.setRecruiter(recruiter);
        jobPosting.setStatus(StatusJobPosting.PENDING);

        jobPostingRepo.save(jobPosting);
    }

//    @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
    @Override
    public List<JobPostingResponse> getAllJobPostings() {
        return jobPostingMapper.
                toJobPostingResponseList(jobPostingRepo.findAllByStatus(StatusJobPosting.ACTIVE));
    }

}
