package com.fpt.careermate.services.resume_services.service.mapper;

import com.fpt.careermate.services.resume_services.domain.Certificate;
import com.fpt.careermate.services.resume_services.service.dto.request.CertificateRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.CertificateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CertificateMapper {
    @Mapping(target = "certificateId", ignore = true)
    @Mapping(target = "resume", ignore = true)
    Certificate toEntity(CertificateRequest request);

    CertificateResponse toResponse(Certificate certificate);

    @Mapping(target = "certificateId", ignore = true)
    @Mapping(target = "resume", ignore = true)
    void updateEntity(CertificateRequest request, @MappingTarget Certificate certificate);
}

