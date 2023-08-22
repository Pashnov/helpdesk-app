package org.axp.entity;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Data;

@Data
@Entity
@PropertyStrategy(mutable = false)
public class Project {

    @PartitionKey
    @CqlName("project_id")
    private final String id;
    private final String name;
    // Project-specific configuration data.
//    private Map<String, String> configuration;

}
