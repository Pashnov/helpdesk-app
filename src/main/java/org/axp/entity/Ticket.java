package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public record Ticket(

        @PartitionKey
        String projectId, // The ID of the project to which the ticket belongs.
        @ClusteringColumn
        @CqlName("ticket_id")
        int id,

        String name,
        String description,
        @CqlName("is_active")
        boolean active,
        LocalDateTime dateSubmitted,
        Priority priority,
        UUID reporterUser,
        UUID assigneeUser,

        int statusId // The ID of the ticket status in the database.
) {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}
