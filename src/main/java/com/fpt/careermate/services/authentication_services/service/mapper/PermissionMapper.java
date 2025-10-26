package com.fpt.careermate.services.authentication_services.service.mapper;

import com.fpt.careermate.services.authentication_services.domain.Permission;
import com.fpt.careermate.services.authentication_services.service.dto.request.PermissionRequest;
import com.fpt.careermate.services.authentication_services.service.dto.response.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
