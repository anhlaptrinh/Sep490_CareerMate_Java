package com.fpt.careermate.services.order_services.service;

import com.fpt.careermate.common.constant.StatusOrder;
import com.fpt.careermate.services.account_services.domain.Account;
import com.fpt.careermate.services.authentication_services.service.AuthenticationImp;
import com.fpt.careermate.services.profile_services.domain.Candidate;
import com.fpt.careermate.services.order_services.domain.Order;
import com.fpt.careermate.services.order_services.domain.Package;
import com.fpt.careermate.services.profile_services.repository.CandidateRepo;
import com.fpt.careermate.services.order_services.repository.OrderRepo;
import com.fpt.careermate.services.order_services.repository.PackageRepo;
import com.fpt.careermate.services.order_services.service.dto.response.OrderResponse;
import com.fpt.careermate.services.order_services.service.impl.OrderService;
import com.fpt.careermate.services.order_services.service.mapper.OrderMapper;
import com.fpt.careermate.common.util.PaymentUtil;
import com.fpt.careermate.common.exception.AppException;
import com.fpt.careermate.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderImp implements OrderService {

    OrderRepo orderRepo;
    PackageRepo packageRepo;
    CandidateRepo candidateRepo;
    OrderMapper orderMapper;
    AuthenticationImp authenticationImp;
    PaymentUtil paymentUtil;

    @PreAuthorize("hasRole('CANDIDATE')")
    @Override
    @Transactional
    public String createOrder(int packageId) {
        Candidate currentCandidate = getCurrentCandidate();

        Package pkg = packageRepo.findById(packageId)
                .orElseThrow(() -> new AppException(ErrorCode.PACKAGE_NOT_FOUND));

        Order order = new Order();
        order.setOrderCode(paymentUtil.generateOrderCodeUuid());
        order.setCandidate(currentCandidate);
        order.setCandidatePackage(pkg);
        order.setAmount(pkg.getPrice());
        order.setPackageNameSnapshot(pkg.getName());
        order.setPackagePriceSnapshot(pkg.getPrice());
        order.setStatus(StatusOrder.PENDING);
        order.setCreateAt(LocalDate.now());
        Order savedOrder = orderRepo.save(order);

        return savedOrder.getOrderCode();
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @Override
    public void deleteOrder(int id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        if (!order.getStatus().equals(StatusOrder.PENDING)) {
            throw new AppException(ErrorCode.CANNOT_DELETE_ORDER);
        }

        order.setStatus(StatusOrder.CANCELLED);
        order.setCancelledAt(LocalDate.now());
        orderRepo.save(order);
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @Override
    public String checkOrderStatus(int id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        return order.getStatus();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<OrderResponse> getOrderList() {
        return orderMapper.toOrderResponseList(orderRepo.findAll());
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @Override
    public List<OrderResponse> myOrderList() {
        Candidate currentCandidate = getCurrentCandidate();

        List<Order> orders = orderRepo.findByCandidate_CandidateId(currentCandidate.getCandidateId());
        return orderMapper.toOrderResponseList(orders);
    }
    
    private Candidate getCurrentCandidate(){
        Account currentAccount = authenticationImp.findByEmail();
        Optional<Candidate> candidate = candidateRepo.findByAccount_Id(Integer.valueOf(currentAccount.getId()));
        Candidate currentCandidate = candidate.get();
        return currentCandidate;
    }
}
