package com.acme.fueltrack.backend.iam.domain.model.aggregates;

import com.acme.fueltrack.backend.iam.domain.model.valueobjects.Roles;
import com.acme.fueltrack.backend.iam.domain.model.valueobjects.Permissions;
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
    private Roles rol;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Permissions permissions;


    public Profile(Roles rol) {
        this.rol = rol;
        this.permissions = Permissions.WRITE_USERS;
    }

    public String getStringName() {
        return rol.name();
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
