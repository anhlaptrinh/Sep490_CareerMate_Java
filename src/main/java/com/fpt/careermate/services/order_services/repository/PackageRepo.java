package com.fpt.careermate.services.order_services.repository;

import com.fpt.careermate.services.order_services.domain.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepo extends JpaRepository<Package,Integer> {
}
