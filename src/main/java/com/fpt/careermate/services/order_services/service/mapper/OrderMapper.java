package com.fpt.careermate.services.order_services.service.mapper;

import com.fpt.careermate.services.order_services.domain.CandidateOrder;
import com.fpt.careermate.services.order_services.service.dto.response.CandidateOrderResponse;
import com.fpt.careermate.services.order_services.service.dto.response.MyCandidateOrderResponse;
import com.fpt.careermate.services.order_services.service.dto.response.PageCandidateOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "candidatePackage.name", target = "packageName")
    MyCandidateOrderResponse toOrderResponse(CandidateOrder candidateOrder);

    @Mapping(source = "candidatePackage.name", target = "packageName")
    @Mapping(source = "candidate.fullName", target = "candidateName")
    CandidateOrderResponse toCandidateOrderResponse(CandidateOrder candidateOrder);

    // Chuyển page sang PageCandidateOrderResponse
    PageCandidateOrderResponse toPageCandidateOrderResponse(Page<CandidateOrder> candidateOrderPage);
}
