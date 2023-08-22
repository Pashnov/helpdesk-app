package org.axp.rest;

import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDto {

    private final ProjectDto project;
    @Null(message = "This 'id' is set automatically, should be null in the request")
    private final Integer id;

    private final String name;
    private final String description;
    private final boolean isActive;
    @Null(message = "This 'dateSubmitted' is set automatically, should be null in the request")
    private final LocalDateTime dateSubmitted;
    private final String priority; // LOW, MEDIUM, HIGH;
    private final UserDto createdByUser;
    private final UserDto assignedToUser;

    private final TicketStatusDto status;

}