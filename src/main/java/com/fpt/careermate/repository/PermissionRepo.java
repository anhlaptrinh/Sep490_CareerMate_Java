package com.fpt.careermate.repository;

import com.fpt.careermate.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepo extends JpaRepository<Permission, String> {
}
