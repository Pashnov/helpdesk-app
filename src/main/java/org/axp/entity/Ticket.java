package org.axp.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Ticket {

    private int ticketId;
    private String description;
    private boolean isOpen;
    private LocalDate dateSubmitted;
    private Priority priority;
    private User createdByUser;
    private User assignedToUser;
    private String response;

    private int statusId; // The ID of the ticket status in the database.
    private int projectId; // The ID of the project to which the ticket belongs.


    // Constructor, getters, and setters.

    public enum Priority {
        LOW, MEDIUM, HIGH;
    }
}
