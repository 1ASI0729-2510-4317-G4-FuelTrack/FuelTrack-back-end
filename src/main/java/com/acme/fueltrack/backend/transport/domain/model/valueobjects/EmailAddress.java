package com.acme.fueltrack.backend.transport.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

@Embeddable
public record EmailAddress(String email) {
    public EmailAddress(){
        this(null);
    }
    public EmailAddress{
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email address is required");
        }
    }

}