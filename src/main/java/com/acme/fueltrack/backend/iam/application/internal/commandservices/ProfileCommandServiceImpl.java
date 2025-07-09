package com.acme.fueltrack.backend.iam.application.internal.commandservices;

import com.acme.fueltrack.backend.iam.domain.model.commands.SeedRolesCommand;
import com.acme.fueltrack.backend.iam.domain.model.aggregates.Profile;
import com.acme.fueltrack.backend.iam.domain.model.valueobjects.Roles;
import com.acme.fueltrack.backend.iam.domain.services.ProfileCommandService;
import com.acme.fueltrack.backend.iam.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!profileRepository.existsByRol(role)) {
                profileRepository.save(new Profile(Roles.valueOf(role.name())));
            }
        } );
    }
}
