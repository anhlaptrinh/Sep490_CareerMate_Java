package com.fpt.careermate.services.authentication_services.service.impl;

import com.fpt.careermate.services.authentication_services.service.dto.request.RoleRequest;
import com.fpt.careermate.services.authentication_services.service.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest request);
    List<RoleResponse> getAll();
    void delete(String roleName);
}
