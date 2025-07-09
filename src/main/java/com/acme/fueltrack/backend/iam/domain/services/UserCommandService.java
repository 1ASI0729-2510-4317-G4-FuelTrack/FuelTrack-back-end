package com.acme.fueltrack.backend.iam.domain.services;

import com.acme.fueltrack.backend.iam.domain.model.aggregates.User;
import com.acme.fueltrack.backend.iam.domain.model.commands.SignInCommand;
import com.acme.fueltrack.backend.iam.domain.model.commands.SignUpCommand;
import com.acme.fueltrack.backend.iam.domain.model.commands.UpdateUserCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    Optional<User> handle(SignUpCommand command);

    Optional<User> handle(UpdateUserCommand command);

}
