package com.fpt.careermate.services.job_services.service.mapper;

import com.fpt.careermate.services.job_services.domain.JdSkill;
import com.fpt.careermate.services.job_services.service.dto.response.JdSkillResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JdSkillMapper {
    List<JdSkillResponse> toSetSkillResponse(List<JdSkill> jdSkill);
}
