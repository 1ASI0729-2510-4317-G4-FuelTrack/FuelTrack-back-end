package com.acme.fueltrack.backend.orders.interfaces.rest;

import com.acme.fueltrack.backend.orders.application.internal.services.FuelOrderService;
import com.acme.fueltrack.backend.orders.domain.model.aggregates.FuelOrder;
import com.acme.fueltrack.backend.orders.domain.model.aggregates.OrderPayment;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.PaymentStatus;
import com.acme.fueltrack.backend.orders.infrastuctrure.persistence.OrderPaymentRepository;
import com.acme.fueltrack.backend.orders.interfaces.rest.resources.CreateFuelOrderResource;
import com.acme.fueltrack.backend.orders.interfaces.rest.resources.CompletePaymentResource;
import com.acme.fueltrack.backend.orders.interfaces.rest.transform.FuelOrderMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class FuelOrderController {

    private final FuelOrderService service;
    private final FuelOrderMapper mapper;
    private final OrderPaymentRepository paymentRepository;

    public FuelOrderController(FuelOrderService service,
                               FuelOrderMapper mapper,
                               OrderPaymentRepository paymentRepository) {
        this.service = service;
        this.mapper = mapper;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping
    public ResponseEntity<FuelOrder> createOrder(@RequestBody CreateFuelOrderResource resource) {
        FuelOrder order = service.createOrder(
                mapper.toCommand(resource).requesterId(),
                mapper.toCommand(resource).fuelType(),
                mapper.toCommand(resource).quantity(),
                mapper.toCommand(resource).note()
        );
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<FuelOrder>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderId) {
        service.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{orderId}/payment")
    public ResponseEntity<?> completePayment(
            @PathVariable UUID orderId,
            @RequestBody CompletePaymentResource paymentResource) {

        final double PRICE_PER_GALLON = 5.0;

        OrderPayment payment = paymentRepository.findAll()
                .stream()
                .filter(p -> p.getFuelOrder().getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payment not found for order"));

        double requiredAmount = payment.getFuelOrder().getQuantity() * PRICE_PER_GALLON;

        if (paymentResource.amount() <= 0) {
            return ResponseEntity.badRequest().body("El monto debe ser mayor a cero.");
        }

        if (paymentResource.amount() < requiredAmount) {
            return ResponseEntity.badRequest()
                    .body("El monto ingresado no cubre la cantidad de galones solicitados. Monto mínimo: $" + requiredAmount);
        }

        payment.completePayment(paymentResource.amount(), paymentResource.method());
        paymentRepository.save(payment);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/{orderId}/process")
    public ResponseEntity<FuelOrder> processOrder(@PathVariable UUID orderId) {
        FuelOrder processedOrder = service.processOrder(orderId);
        return ResponseEntity.ok(processedOrder);
    }
    @GetMapping("/pending-payments")
    public ResponseEntity<List<OrderPayment>> getPendingPayments() {
        List<OrderPayment> pendingPayments = paymentRepository.findByStatus(PaymentStatus.PENDING);
        return ResponseEntity.ok(pendingPayments);
    }

}
