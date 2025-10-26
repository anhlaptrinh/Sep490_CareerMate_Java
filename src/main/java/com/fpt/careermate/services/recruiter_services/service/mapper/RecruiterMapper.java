package com.fpt.careermate.services.recruiter_services.service.mapper;

import com.fpt.careermate.services.recruiter_services.domain.Recruiter;
import com.fpt.careermate.services.recruiter_services.service.dto.request.RecruiterCreationRequest;
import com.fpt.careermate.services.recruiter_services.service.dto.response.NewRecruiterResponse;
import com.fpt.careermate.services.recruiter_services.service.dto.response.RecruiterApprovalResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecruiterMapper {
    Recruiter toRecruiter(RecruiterCreationRequest request);

    NewRecruiterResponse toNewRecruiterResponse(Recruiter recruiter);

    @Mapping(source = "id", target = "recruiterId")
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.status", target = "accountStatus")
    @Mapping(target = "accountRole", ignore = true) // Set manually in service
    RecruiterApprovalResponse toRecruiterApprovalResponse(Recruiter recruiter);
}
