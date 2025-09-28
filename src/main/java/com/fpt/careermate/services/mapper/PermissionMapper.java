package com.fpt.careermate.services.mapper;

import com.fpt.careermate.domain.Permission;
import com.fpt.careermate.services.dto.request.PermissionRequest;
import com.fpt.careermate.services.dto.response.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
