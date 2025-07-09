package com.acme.fueltrack.backend.iam.domain.model.aggregates;

import com.acme.fueltrack.backend.iam.domain.model.valueobjects.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles name;

    public Profile(Roles name) { this.name = name; }

    public String getStringName() {
        return name.name();
    }

    public static Profile getDefaultProfile() { return new Profile(Roles.ROLE_ADMIN); }

    public static Profile toProfileFromName(String name) {
        return new Profile(Roles.valueOf(name));
    }

    public static List<Profile> validateRoleSet(List<Profile> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultProfile());
        }
        return roles;
    }
}
