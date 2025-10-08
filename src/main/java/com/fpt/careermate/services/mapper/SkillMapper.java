package com.fpt.careermate.services.mapper;

import com.fpt.careermate.domain.Skill;
import com.fpt.careermate.services.dto.response.SkillResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    List<SkillResponse> toSetSkillResponse(List<Skill> skill);
}
