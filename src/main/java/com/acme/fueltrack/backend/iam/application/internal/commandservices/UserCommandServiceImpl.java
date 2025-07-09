package com.acme.fueltrack.backend.iam.application.internal.commandservices;

import com.acme.fueltrack.backend.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.fueltrack.backend.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.fueltrack.backend.iam.domain.model.aggregates.Profile;
import com.acme.fueltrack.backend.iam.domain.model.aggregates.User;
import com.acme.fueltrack.backend.iam.domain.model.commands.SignInCommand;
import com.acme.fueltrack.backend.iam.domain.model.commands.SignUpCommand;
import com.acme.fueltrack.backend.iam.domain.model.commands.UpdateUserCommand;
import com.acme.fueltrack.backend.iam.domain.model.valueobjects.Roles;
import com.acme.fueltrack.backend.iam.domain.services.UserCommandService;
import com.acme.fueltrack.backend.iam.infrastructure.persistence.jpa.repositories.ProfileRepository;
import com.acme.fueltrack.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final ProfileRepository profileRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty())
            throw new RuntimeException("Email not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email()))
            throw new RuntimeException("Email already exists");
        var roles = command.roles().stream().map(role -> profileRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        var user = new User(
                command.username(),
                command.email(),
                hashingService.encode(command.password()),
                roles
        );
        user.setProfileId(roles.getFirst().getId());
        userRepository.save(user);
        return userRepository.findByEmail(command.email());
    }

    @Override
    @Transactional
    public Optional<User> handle(UpdateUserCommand command) {
        return userRepository.findById(command.userId())
                .map(user -> {
                    user.setUsername(command.username());
                    user.setEmail(command.email());

                    if (command.roles() != null && !command.roles().isEmpty()) {
                        user.setProfiles(new HashSet<>());

                        String roleName = command.roles().iterator().next(); // Tomamos el primer rol
                        Profile newProfile = profileRepository.findByName(Roles.valueOf(roleName))
                                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));

                        user.setProfileId(newProfile.getId());

                        user.addProfile(newProfile);
                    }

                    return userRepository.save(user);
                });
    }


}