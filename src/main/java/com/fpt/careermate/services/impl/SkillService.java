package com.fpt.careermate.services.impl;

import com.fpt.careermate.services.dto.response.SkillResponse;

import java.util.List;

public interface SkillService {
    void createSkill(String name);
    List<SkillResponse> getAllSkill();
}
