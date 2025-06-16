package com.acme.fueltrack.backend.orders.domain.services;

import com.acme.fueltrack.backend.orders.domain.model.aggregates.FuelOrder;
import com.acme.fueltrack.backend.orders.domain.model.commands.FuelOrderCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuelOrderService {

    // Simulación de repositorio en memoria
    private final List<FuelOrder> orders = new ArrayList<>();

    public FuelOrder handle(FuelOrderCommand command) {
        FuelOrder order = new FuelOrder(command.requesterId(), command.fuelType(), command.quantity());
        orders.add(order); // simulamos un "save"
        return order;
    }

    public List<FuelOrder> getAllOrders() {
        return orders;
    }
}
