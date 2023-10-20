package org.axp.domain;

import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record TicketDto (
    ProjectDto project,
    @Null(message = "This 'id' is set automatically, should be null in the request")
    Integer id,

    String name,
    String description,
    boolean active,
    @Null(message = "This 'dateSubmitted' is set automatically, should be null in the request")
    LocalDateTime dateSubmitted,
    String priority, // LOW, MEDIUM, HIGH,
    UserDto reporterUser,
    UserDto assigneeUser,

    TicketStatusDto status
){}
