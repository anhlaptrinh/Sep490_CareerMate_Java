package com.fpt.careermate.services;

import com.fpt.careermate.domain.Skill;
import com.fpt.careermate.repository.SkillRepo;
import com.fpt.careermate.services.dto.response.SkillResponse;
import com.fpt.careermate.services.impl.SkillService;
import com.fpt.careermate.services.mapper.SkillMapper;
import com.fpt.careermate.web.exception.AppException;
import com.fpt.careermate.web.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SkillImp implements SkillService {

    SkillRepo skillRepo;
    SkillMapper skillMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void createSkill(String name) {
        // Check skill name
        Optional<Skill> exSkill = skillRepo.findSkillByName(name);
        if (exSkill.isPresent()) throw new AppException(ErrorCode.SKILL_EXISTED);

        Skill skill = new Skill();
        skill.setName(name);
        skillRepo.save(skill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<SkillResponse> getAllSkill() {
        return skillMapper.toSetSkillResponse(skillRepo.findAll());
    }

}
