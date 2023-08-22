package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@PropertyStrategy(mutable = false)
public class Ticket {

    @PartitionKey
    private final String projectId; // The ID of the project to which the ticket belongs.
    @ClusteringColumn
    @CqlName("ticket_id")
    private final int id;

    private final String name;
    private final String description;
    @CqlName("is_active")
    private final boolean active;
    private final LocalDateTime dateSubmitted;
    private final Priority priority;
    private final UUID createdByUser;
    private final UUID assignedToUser;

    private final int statusId; // The ID of the ticket status in the database.


    // Constructor, getters, and setters.

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}
