package com.fpt.careermate.services.resume_services.service.mapper;

import com.fpt.careermate.services.resume_services.domain.ForeignLanguage;
import com.fpt.careermate.services.resume_services.service.dto.request.ForeignLanguageRequest;
import com.fpt.careermate.services.resume_services.service.dto.response.ForeignLanguageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ForeignLanguageMapper {
    @Mapping(target = "foreignLanguageId", ignore = true)
    @Mapping(target = "resume", ignore = true)
    ForeignLanguage toEntity(ForeignLanguageRequest request);

    ForeignLanguageResponse toResponse(ForeignLanguage foreignLanguage);

    @Mapping(target = "foreignLanguageId", ignore = true)
    @Mapping(target = "resume", ignore = true)
    void updateEntity(ForeignLanguageRequest request, @MappingTarget ForeignLanguage foreignLanguage);
}

