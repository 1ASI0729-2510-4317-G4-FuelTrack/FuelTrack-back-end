package com.acme.fueltrack.backend.orders.application.services;

import com.acme.fueltrack.backend.orders.domain.model.aggregates.FuelOrder;
import com.acme.fueltrack.backend.orders.domain.model.aggregates.OrderPayment;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.PaymentStatus;
import com.acme.fueltrack.backend.orders.infrastuctrure.persistence.FuelOrderRepository;
import com.acme.fueltrack.backend.orders.infrastuctrure.persistence.OrderPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FuelOrderService {

    private final FuelOrderRepository fuelOrderRepository;
    private final OrderPaymentRepository orderPaymentRepository;

    public FuelOrderService(FuelOrderRepository fuelOrderRepository,
                            OrderPaymentRepository orderPaymentRepository) {
        this.fuelOrderRepository = fuelOrderRepository;
        this.orderPaymentRepository = orderPaymentRepository;
    }

    @Transactional
    public FuelOrder createOrder(UUID requesterId, FuelType fuelType, double quantity,String note) {
        FuelOrder order = new FuelOrder(requesterId, fuelType, quantity, note);
        FuelOrder savedOrder = fuelOrderRepository.save(order);

        OrderPayment payment = new OrderPayment(savedOrder);
        orderPaymentRepository.save(payment);

        return savedOrder;
    }

    public List<FuelOrder> getOrdersByRequester(UUID requesterId) {
        return fuelOrderRepository.findByRequesterId(requesterId);
    }

    @Transactional
    public FuelOrder processOrder(UUID orderId) {
        FuelOrder order = fuelOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderPayment payment = orderPaymentRepository.findAll()
                .stream()
                .filter(p -> p.getFuelOrder().getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payment not found for order"));

        if (payment.getStatus() != PaymentStatus.COMPLETED) {
            throw new IllegalStateException("Cannot process order: payment not completed.");
        }

        order.markAsProcessed();
        return fuelOrderRepository.save(order);
    }



    public List<FuelOrder> getAllOrders() {
        return fuelOrderRepository.findAll();
    }
}
