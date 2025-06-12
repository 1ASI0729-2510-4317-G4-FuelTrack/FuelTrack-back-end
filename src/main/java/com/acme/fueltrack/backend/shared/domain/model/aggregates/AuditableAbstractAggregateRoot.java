package com.acme.fueltrack.backend.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base abstract class for all aggregate roots that require auditing fields.
 * This class provides standard fields such as ID, creation date, and update date,
 * and is meant to be extended by aggregate root entities across the domain layer.
 * @param <T> the type of the aggregate root extending this class
 *
 * @author Luciana Choquehuanca
 */
@Getter
@EntityListeners(AuditingEntityListener.class) // Enables auditing features (e.g., auto-setting createdAt and updatedAt)
@MappedSuperclass // Indicates this class is a base for other JPA entities, but is not itself an entity
public abstract class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {

    /** The unique identifier of the aggregate root (auto-generated). */
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Timestamp for when the entity was first created (set automatically). */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /** Timestamp for when the entity was last modified (set automatically). */
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}