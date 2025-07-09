package com.acme.fueltrack.backend.iam.interfaces.rest;

import com.acme.fueltrack.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.fueltrack.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.acme.fueltrack.backend.iam.domain.services.UserQueryService;
import com.acme.fueltrack.backend.iam.interfaces.rest.resources.ProfileResource;
import com.acme.fueltrack.backend.iam.interfaces.rest.resources.UserResource;
import com.acme.fueltrack.backend.iam.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.acme.fueltrack.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acme.fueltrack.backend.iam.domain.services.UserCommandService;
import com.acme.fueltrack.backend.iam.interfaces.rest.resources.UpdateUserResource;
import com.acme.fueltrack.backend.iam.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;


import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Available User Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersController(
            UserQueryService userQueryService,
            UserCommandService userCommandService
            ) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }


    @GetMapping
    @Operation(summary = "Get all users", description = "Get all the users available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping(value = "/{userId}")
    @Operation(summary = "Get user by id", description = "Get the user with the given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")})
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user", description = "Update an existing user's information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "400", description = "Invalid request data."),
            @ApiResponse(responseCode = "401", description = "Unauthorized.")
    })
    public ResponseEntity<UserResource> updateUser(
            @PathVariable Long userId,
            @RequestBody UpdateUserResource updateUserResource) {

        var updateUserCommand = UpdateUserCommandFromResourceAssembler
                .toCommandFromResource(userId, updateUserResource);

        var updatedUser = userCommandService.handle(updateUserCommand);

        if (updatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler
                .toResourceFromEntity(updatedUser.get());

        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{userId}/profiles")
    @Operation(summary = "Get user profiles", description = "Get all profiles for a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profiles retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<List<ProfileResource>> getUserProfiles(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var profileResources = user.get().getProfiles().stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(profileResources);
    }
}
