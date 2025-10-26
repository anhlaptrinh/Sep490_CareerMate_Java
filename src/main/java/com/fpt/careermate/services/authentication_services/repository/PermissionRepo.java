package com.fpt.careermate.services.authentication_services.repository;

import com.fpt.careermate.services.authentication_services.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, String> {
}
