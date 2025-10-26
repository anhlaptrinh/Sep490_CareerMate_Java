package com.fpt.careermate.services.order_services.service.impl;


import com.fpt.careermate.services.order_services.service.dto.request.PackageCreationRequest;
import com.fpt.careermate.services.order_services.service.dto.response.PackageResponse;

import java.util.List;

public interface PackageService {
    PackageResponse createPackage(PackageCreationRequest request);
    List<PackageResponse> getPackageList();
}
