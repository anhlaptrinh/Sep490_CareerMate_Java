package com.fpt.careermate.services.order_services.service;

import com.fpt.careermate.common.constant.StatusOrder;
import com.fpt.careermate.services.account_services.domain.Account;
import com.fpt.careermate.services.authentication_services.service.AuthenticationImp;
import com.fpt.careermate.services.order_services.service.dto.response.CandidateOrderResponse;
import com.fpt.careermate.services.order_services.service.dto.response.MyCandidateOrderResponse;
import com.fpt.careermate.services.order_services.service.dto.response.PageCandidateOrderResponse;
import com.fpt.careermate.services.profile_services.domain.Candidate;
import com.fpt.careermate.services.order_services.domain.CandidateOrder;
import com.fpt.careermate.services.order_services.domain.CandidatePackage;
import com.fpt.careermate.services.profile_services.repository.CandidateRepo;
import com.fpt.careermate.services.order_services.repository.CandidateOrderRepo;
import com.fpt.careermate.services.order_services.repository.PackageRepo;
import com.fpt.careermate.services.order_services.service.impl.OrderService;
import com.fpt.careermate.services.order_services.service.mapper.OrderMapper;
import com.fpt.careermate.common.util.PaymentUtil;
import com.fpt.careermate.common.exception.AppException;
import com.fpt.careermate.common.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderImp implements OrderService {

    CandidateOrderRepo candidateOrderRepo;
    PackageRepo packageRepo;
    CandidateRepo candidateRepo;
    OrderMapper orderMapper;
    AuthenticationImp authenticationImp;

    @Transactional
    public void createOrder(String packageName, Candidate currentCandidate) {
        CandidatePackage pkg = packageRepo.findByName(packageName);

        CandidateOrder candidateOrder = new CandidateOrder();
        candidateOrder.setCandidate(currentCandidate);
        candidateOrder.setCandidatePackage(pkg);
        candidateOrder.setAmount(pkg.getPrice());
        candidateOrder.setStatus(StatusOrder.PAID);
        candidateOrder.setCreateAt(LocalDate.now());
        candidateOrder.setActive(true);
        candidateOrder.setStartDate(LocalDate.now());
        candidateOrder.setEndDate(LocalDate.now().plusDays(pkg.getDurationDays()));

        candidateOrderRepo.save(candidateOrder);
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @Override
    public void cancelOrder(int id) {
        CandidateOrder candidateOrder = candidateOrderRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        if (candidateOrder.getStatus().equals(StatusOrder.PAID)) {
            candidateOrder.setStatus(StatusOrder.CANCELLED);
            candidateOrder.setCancelledAt(LocalDate.now());
            candidateOrder.setActive(false);
            candidateOrderRepo.save(candidateOrder);
        }
        else {
            throw new AppException(ErrorCode.CANNOT_DELETE_ORDER);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public PageCandidateOrderResponse getOrderList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createAt").descending());

        Page<CandidateOrder> candidateOrders = candidateOrderRepo.findAll(pageable);

        List<CandidateOrderResponse> candidateOrderResponses = new ArrayList<>();
        candidateOrders.getContent().forEach(candidateOrder -> {
            CandidateOrderResponse response = orderMapper.toCandidateOrderResponse(candidateOrder);
            candidateOrderResponses.add(response);
        });

        PageCandidateOrderResponse pageCandidateOrderResponse = orderMapper.toPageCandidateOrderResponse(candidateOrders);
        pageCandidateOrderResponse.setContent(candidateOrderResponses);

        return pageCandidateOrderResponse;
    }

    @PreAuthorize("hasRole('CANDIDATE')")
    @Override
    public MyCandidateOrderResponse myCandidatePackage() {
        Candidate currentCandidate = getCurrentCandidate();

        Optional<CandidateOrder> candidateOrder = candidateOrderRepo.findByCandidate_CandidateIdAndIsActiveTrue(currentCandidate.getCandidateId());

        if(candidateOrder.isEmpty()) throw new AppException(ErrorCode.USING_FREE_PACAKGE);

        return orderMapper.toOrderResponse(candidateOrder.get());
    }
    
    private Candidate getCurrentCandidate(){
        Account currentAccount = authenticationImp.findByEmail();
        Optional<Candidate> candidate = candidateRepo.findByAccount_Id(Integer.valueOf(currentAccount.getId()));
        Candidate currentCandidate = candidate.get();
        return currentCandidate;
    }

    public void updateCandidateOrder(CandidateOrder exstingCandidateOrder, String packageName){
        CandidatePackage pkg = packageRepo.findByName(packageName);

        exstingCandidateOrder.setCandidatePackage(pkg);
        exstingCandidateOrder.setAmount(pkg.getPrice());
        exstingCandidateOrder.setStatus(StatusOrder.PAID);
        exstingCandidateOrder.setCreateAt(LocalDate.now());
        exstingCandidateOrder.setActive(true);
        exstingCandidateOrder.setStartDate(LocalDate.now());
        exstingCandidateOrder.setEndDate(LocalDate.now().plusDays(pkg.getDurationDays()));
        exstingCandidateOrder.setCancelledAt(null);

        candidateOrderRepo.save(exstingCandidateOrder);
    }

    public CandidatePackage getPackageByName(String packageName){
        return packageRepo.findByName(packageName);
    }

    // Check if candidate has an active order
    public boolean hasActivePackage() {
        Candidate currentCandidate = getCurrentCandidate();
        Optional<CandidateOrder> activeOrder = candidateOrderRepo.findByCandidate_CandidateIdAndIsActiveTrue(currentCandidate.getCandidateId());
        return activeOrder.isPresent();
    }
}
