package com.fpt.careermate.services.authentication_services.service.impl;

import com.fpt.careermate.services.authentication_services.service.dto.request.PermissionRequest;
import com.fpt.careermate.services.authentication_services.service.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    void delete(String permission);

}
