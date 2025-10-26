package com.fpt.careermate.services.authentication_services.repository;

import com.fpt.careermate.services.authentication_services.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,String> {
    Optional<Role> findByName(String name);
}
