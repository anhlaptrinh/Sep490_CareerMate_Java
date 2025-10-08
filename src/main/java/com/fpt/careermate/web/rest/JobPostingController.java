package com.fpt.careermate.web.rest;

import com.fpt.careermate.services.JobPostingImp;
import com.fpt.careermate.services.dto.request.AccountCreationRequest;
import com.fpt.careermate.services.dto.request.JobPostingCreationRequest;
import com.fpt.careermate.services.dto.response.AccountResponse;
import com.fpt.careermate.services.dto.response.ApiResponse;
import com.fpt.careermate.services.dto.response.JobPostingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/jobposting")
@Tag(name = "Job posting", description = "Manage job posting")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class JobPostingController {

    JobPostingImp jobPostingImp;

    @PostMapping
    @Operation(summary = "Recruiter create job posting")
    ApiResponse<String> createJobPosting(@Valid @RequestBody JobPostingCreationRequest request) {
        jobPostingImp.createJobPosting(request);
        return ApiResponse.<String>builder()
                .code(200)
                .message("success")
                .build();
    }

    @GetMapping
    @Operation(summary = "Admin or candidate can view job posting list")
    ApiResponse<List<JobPostingResponse>> getJobPostingList() {
        return ApiResponse.<List<JobPostingResponse>>builder()
                .result(jobPostingImp.getAllJobPostings())
                .code(200)
                .message("success")
                .build();
    }

}
