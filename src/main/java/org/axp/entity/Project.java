package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

@Entity
public record Project(
        @PartitionKey
        @CqlName("project_id")
        String id,
        String name
) {}
