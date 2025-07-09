package com.acme.fueltrack.backend.orders.interfaces.rest;

import com.acme.fueltrack.backend.orders.domain.model.aggregates.OrderPayment;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.PaymentStatus;
import com.acme.fueltrack.backend.orders.infrastuctrure.persistence.OrderPaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final OrderPaymentRepository paymentRepository;

    public PaymentController(OrderPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/pending")
    public ResponseEntity<List<OrderPayment>> getPendingPayments() {
        List<OrderPayment> pendingPayments = paymentRepository.findAll()
                .stream()
                .filter(payment -> payment.getStatus() == PaymentStatus.PENDING)
                .toList();

        return ResponseEntity.ok(pendingPayments);
    }
}
