package com.acme.fueltrack.backend.transport.domain.model.queries;

import com.acme.fueltrack.backend.transport.domain.model.valueobjects.EmailAddress;

public record GetDriverByEmailQuery(EmailAddress email) {
}
