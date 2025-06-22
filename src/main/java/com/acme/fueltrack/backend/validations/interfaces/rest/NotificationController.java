package com.acme.fueltrack.backend.validations.interfaces.rest;

import com.acme.fueltrack.backend.validations.domain.model.aggregates.Notification;
import com.acme.fueltrack.backend.validations.domain.model.commands.CreateNotificationCommand;
import com.acme.fueltrack.backend.validations.domain.model.commands.MarkNotificationAsReadCommand;
import com.acme.fueltrack.backend.validations.domain.services.NotificationCommandService;
import com.acme.fueltrack.backend.validations.domain.services.NotificationQueryService;
import com.acme.fueltrack.backend.validations.interfaces.rest.resources.CreateNotificationResource;
import com.acme.fueltrack.backend.validations.interfaces.rest.resources.NotificationResource;
import com.acme.fueltrack.backend.validations.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.acme.fueltrack.backend.validations.interfaces.rest.transform.NotificationResourceFromEntityAssembler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationCommandService commandService;
    private final NotificationQueryService queryService;

    public NotificationController(NotificationCommandService commandService, NotificationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource resource) {
        CreateNotificationCommand command = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(resource);
        return commandService.handle(command)
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}/mark-as-read")
    public ResponseEntity<NotificationResource> markAsRead(@PathVariable Long id) {
        MarkNotificationAsReadCommand command = new MarkNotificationAsReadCommand(id);
        return commandService.markNotificationAsRead(command)
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResource>> getAllByUserId(@PathVariable Long userId) {
        List<Notification> notifications = queryService.getAllNotificationsByUserId(userId);
        List<NotificationResource> resources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResource>> getUnreadByUserId(@PathVariable Long userId) {
        List<Notification> notifications = queryService.getUnreadNotificationsByUserId(userId);
        List<NotificationResource> resources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResource> getById(@PathVariable Long id) {
        Optional<Notification> result = queryService.getNotificationById(id);
        return result
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
