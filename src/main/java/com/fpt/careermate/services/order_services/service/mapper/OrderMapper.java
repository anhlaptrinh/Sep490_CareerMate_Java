package com.fpt.careermate.services.order_services.service.mapper;

import com.fpt.careermate.services.order_services.domain.Order;
import com.fpt.careermate.services.order_services.service.dto.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "candidate.candidateId", target = "candidateId")
    @Mapping(source = "candidatePackage.id", target = "packageId")
    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toOrderResponseList (List<Order> orders);
}
