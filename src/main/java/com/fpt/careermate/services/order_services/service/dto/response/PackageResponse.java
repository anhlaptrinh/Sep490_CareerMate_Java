package com.fpt.careermate.services.order_services.service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackageResponse {
    String name;
    Long price;
    int durationDays;
    List<EntitlementResponse> entitlements;
}
