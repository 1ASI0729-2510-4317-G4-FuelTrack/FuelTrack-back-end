package com.acme.fueltrack.backend.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {

}
