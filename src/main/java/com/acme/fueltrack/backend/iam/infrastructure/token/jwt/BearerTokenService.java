package com.acme.fueltrack.backend.iam.infrastructure.token.jwt;

import com.acme.fueltrack.backend.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest token);

    String generateToken(Authentication authentication);
}