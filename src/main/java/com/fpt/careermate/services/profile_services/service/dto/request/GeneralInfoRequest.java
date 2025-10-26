package com.fpt.careermate.services.profile_services.service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralInfoRequest {
    String jobLevel;
    Integer experience;
    List<IndustryExperienceRequest> industryExperiences;
    List<WorkModelRequest> workModels;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class IndustryExperienceRequest {
        String fieldName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class WorkModelRequest {
        String name;
    }
}
