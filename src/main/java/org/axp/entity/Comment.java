package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public record Comment(

        @PartitionKey
        String projectId, // The ID of the project to which the ticket belongs.
        @ClusteringColumn(1)
        int ticketId,
        @ClusteringColumn(2)
        LocalDateTime dateSubmitted,

        String content,
        UUID userId
) {}
