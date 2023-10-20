package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

@Entity
public record TicketStatus (
    @PartitionKey
    @CqlName("status_id")
    int id,
    String name,
    String description
) {}
