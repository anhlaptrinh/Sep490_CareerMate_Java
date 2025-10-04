package com.fpt.careermate.services.mapper;

import com.fpt.careermate.domain.Role;
import com.fpt.careermate.services.dto.request.RoleRequest;
import com.fpt.careermate.services.dto.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
