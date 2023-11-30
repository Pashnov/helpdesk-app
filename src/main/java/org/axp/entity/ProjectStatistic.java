package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

@Entity
public record ProjectStatistic(
        @PartitionKey
        String projectId,
        @ClusteringColumn(1)
        int year,
        @ClusteringColumn(2)
        int week,
        float arrivalRate, // λ lambda
        float serviceRate // μ mu
) {}
