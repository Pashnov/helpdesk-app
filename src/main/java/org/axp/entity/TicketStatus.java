package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Data;

@Data
@Entity
@PropertyStrategy(mutable = false)
public class TicketStatus {

    @PartitionKey
    @CqlName("status_id")
    private final int id;
    private final String name;
    private final String description;

}
