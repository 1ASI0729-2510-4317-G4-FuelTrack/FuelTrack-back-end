package com.acme.fueltrack.backend.iam.infrastructure.hashing.bcrypt;

import com.acme.fueltrack.backend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
