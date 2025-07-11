package com.acme.fueltrack.backend.transport.interfaces.acl;

public interface DriverContextFacade{

    String createDriver(
            String firstName, String lastName, String dni, String email, String plate, String brand, String model, int year, int tankCapacity
    );

    String fetchDriverByEmail(String email);
    String fetchDriverByDni(String dni);

}
