package com.acme.fueltrack.backend.orders.application.services;

import com.acme.fueltrack.backend.orders.domain.model.aggregates.FuelOrder;
import com.acme.fueltrack.backend.orders.domain.model.valueobjects.FuelType;
import com.acme.fueltrack.backend.orders.infrastuctrure.persistence.FuelOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuelOrderService {

    private final FuelOrderRepository fuelOrderRepository;

    public FuelOrderService(FuelOrderRepository fuelOrderRepository) {
        this.fuelOrderRepository = fuelOrderRepository;
    }

    public FuelOrder createOrder(UUID requesterId, FuelType fuelType, double quantity) {
        FuelOrder order = new FuelOrder(requesterId, fuelType, quantity);
        return fuelOrderRepository.save(order);
    }

    public List<FuelOrder> getOrdersByRequester(UUID requesterId) {
        return fuelOrderRepository.findByRequesterId(requesterId);
    }

    public List<FuelOrder> getAllOrders() {
        return fuelOrderRepository.findAll();
    }
}
