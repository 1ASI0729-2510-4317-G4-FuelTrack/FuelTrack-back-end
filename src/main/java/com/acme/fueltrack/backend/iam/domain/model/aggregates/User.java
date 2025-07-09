package com.acme.fueltrack.backend.iam.domain.model.aggregates;


import com.acme.fueltrack.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditableAbstractAggregateRoot<User> {

    private String username;
    private String email;
    private String password;

    @Column(name = "profile_id")
    private Long profileId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private Set<Profile> profiles;

    public User() {
        this.profiles = new HashSet<>();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profiles = new HashSet<>();
    }

    public User(String username, String email, String password, List<Profile> profiles) {
        this(username, email, password);
        addProfiles(profiles);
    }

    public User addProfile(Profile profile) {
        this.profiles.add(profile);
        return this;
    }

    public User addProfiles(List<Profile> profiles) {
        var validatedRoleSet = Profile.validateRoleSet(profiles);
        this.profiles.addAll(validatedRoleSet);
        return this;
    }

}
