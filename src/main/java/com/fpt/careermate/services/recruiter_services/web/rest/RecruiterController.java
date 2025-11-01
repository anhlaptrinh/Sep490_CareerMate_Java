package com.fpt.careermate.services.recruiter_services.web.rest;

import com.fpt.careermate.common.response.ApiResponse;
import com.fpt.careermate.services.recruiter_services.service.RecruiterImp;
import com.fpt.careermate.services.recruiter_services.service.dto.request.RecruiterCreationRequest;
import com.fpt.careermate.services.recruiter_services.service.dto.response.NewRecruiterResponse;
import com.fpt.careermate.services.recruiter_services.service.dto.response.RecruiterApprovalResponse;
import com.fpt.careermate.services.authentication_services.service.dto.request.RecruiterRegistrationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/recruiter")
@Tag(name = "Recruiter", description = "Recruiter profile management")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RecruiterController {

    RecruiterImp recruiterImp;

    @Operation(
        summary = "Create recruiter profile (Step 2 of recruiter registration)",
        description = "**Recruiter Registration Flow:**\n\n" +
                      "1. User signs up via POST /api/users (gets CANDIDATE role)\n" +
                      "2. User creates recruiter profile via this endpoint (still CANDIDATE role)\n" +
                      "3. Admin reviews profile via GET /api/admin/recruiters/pending\n" +
                      "4. Admin approves via PUT /api/admin/recruiters/{id}/approve (role changes to RECRUITER)\n\n" +
                      "**Note:** After sign up, users can immediately create their recruiter profile with organization info. " +
                      "The profile will be pending until admin approval."
    )
    @PostMapping
    public ApiResponse<NewRecruiterResponse> createRecruiter(@Valid @RequestBody RecruiterCreationRequest request) {
        return ApiResponse.<NewRecruiterResponse>builder()
                .result(recruiterImp.createRecruiter(request))
                .code(200)
                .message("Recruiter profile created successfully. Waiting for admin approval to activate recruiter features.")
                .build();
    }

    @GetMapping("/my-profile")
    @PreAuthorize("hasRole('RECRUITER')")
    @Operation(
        summary = "Get my recruiter profile and status",
        description = "Recruiter can view their own profile including verification status, rejection reason (if rejected), " +
                      "and all organization information. This endpoint shows PENDING, APPROVED, REJECTED, or BANNED status."
    )
    public ApiResponse<RecruiterApprovalResponse> getMyProfile() {
        log.info("Recruiter fetching own profile");
        return ApiResponse.<RecruiterApprovalResponse>builder()
                .result(recruiterImp.getMyRecruiterProfile())
                .code(200)
                .message("Success")
                .build();
    }

    @PutMapping("/update-organization")
    @PreAuthorize("hasRole('RECRUITER')")
    @Operation(
        summary = "Update organization information (only for rejected recruiters)",
        description = "Rejected recruiters can update their organization information and resubmit for admin review. " +
                      "This endpoint is only available for accounts with REJECTED status. " +
                      "After update, the status will change back to PENDING for admin review. " +
                      "**BANNED accounts cannot use this endpoint.**"
    )
    public ApiResponse<String> updateOrganizationInfo(@Valid @RequestBody RecruiterRegistrationRequest.OrganizationInfo orgInfo) {
        log.info("Recruiter attempting to update organization info");
        recruiterImp.updateOrganizationInfo(orgInfo);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Organization information updated successfully. Your profile is now pending admin review again.")
                .build();
    }

}
