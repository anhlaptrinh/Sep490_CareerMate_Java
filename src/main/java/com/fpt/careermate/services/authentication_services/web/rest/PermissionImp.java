package com.fpt.careermate.services.authentication_services.web.rest;

import com.fpt.careermate.services.authentication_services.domain.Permission;
import com.fpt.careermate.services.authentication_services.repository.PermissionRepo;
import com.fpt.careermate.services.authentication_services.service.dto.request.PermissionRequest;
import com.fpt.careermate.services.authentication_services.service.dto.response.PermissionResponse;
import com.fpt.careermate.services.authentication_services.service.impl.PermissionService;
import com.fpt.careermate.services.authentication_services.service.mapper.PermissionMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionImp implements PermissionService {
    PermissionRepo permissionRepository;
    PermissionMapper permissionMapper;
    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
