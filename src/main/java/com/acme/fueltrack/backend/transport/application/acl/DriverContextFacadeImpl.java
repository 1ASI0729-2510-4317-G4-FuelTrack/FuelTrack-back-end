package com.acme.fueltrack.backend.transport.application.acl;

import com.acme.fueltrack.backend.transport.domain.model.commands.CreateDriverCommand;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByDniQuery;
import com.acme.fueltrack.backend.transport.domain.model.queries.GetDriverByEmailQuery;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.Dni;
import com.acme.fueltrack.backend.transport.domain.model.valueobjects.EmailAddress;
import com.acme.fueltrack.backend.transport.domain.services.DriverCommandService;
import com.acme.fueltrack.backend.transport.domain.services.DriverQueryService;
import com.acme.fueltrack.backend.transport.interfaces.acl.DriverContextFacade;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class DriverContextFacadeImpl implements DriverContextFacade {

    private final DriverCommandService driverCommandService;
    private final DriverQueryService driverQueryService;

    public DriverContextFacadeImpl(DriverCommandService driverCommandService, DriverQueryService driverQueryService) {
        this.driverCommandService = driverCommandService;
        this.driverQueryService = driverQueryService;
    }

    @Override
    public String createDriver(String firstName, String lastName, String dni, String email, String plate, String brand, String model, int year, int tankCapacity) {
        var createDriverCommand = new CreateDriverCommand(firstName, lastName, dni, email, plate, brand, model, year, tankCapacity);
        var driver = driverCommandService.handle(createDriverCommand);

        return driver.isEmpty() ? Strings.EMPTY : driver.get().getDni();
    }

    @Override
    public String fetchDriverByEmail(String email) {
        var getDriverByEmailQuery = new GetDriverByEmailQuery(new EmailAddress(email));
        var driver = driverQueryService.handle(getDriverByEmailQuery);

        return driver.isEmpty() ? Strings.EMPTY : driver.get().getEmailAddress();
    }

    @Override
    public String fetchDriverByDni(String dni) {
        var getDriverByDniQuery = new GetDriverByDniQuery(new Dni(dni));
        var driver = driverQueryService.handle(getDriverByDniQuery);

        return driver.isEmpty() ? Strings.EMPTY : driver.get().getDni();
    }



}