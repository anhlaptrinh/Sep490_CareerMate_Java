package com.fpt.careermate.repository;

import com.fpt.careermate.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,String> {
}
