package com.fpt.careermate.repository;

import com.fpt.careermate.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SkillRepo extends JpaRepository<Skill, Integer> {
    Optional<Skill> findSkillByName(String name);
}
