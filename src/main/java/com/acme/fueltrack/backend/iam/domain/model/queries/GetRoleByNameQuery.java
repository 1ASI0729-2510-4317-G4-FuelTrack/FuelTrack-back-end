package com.acme.fueltrack.backend.iam.domain.model.queries;

import com.acme.fueltrack.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles role) {
}
