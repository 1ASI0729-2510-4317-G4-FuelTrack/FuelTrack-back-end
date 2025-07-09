package com.acme.fueltrack.backend.iam.domain.services;

import com.acme.fueltrack.backend.iam.domain.model.aggregates.Profile;
import com.acme.fueltrack.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.acme.fueltrack.backend.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;


public interface ProfileQueryService {

    List<Profile> handle(GetAllRolesQuery query);

    Optional<Profile> handle(GetRoleByNameQuery query);
}
