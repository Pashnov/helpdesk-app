package org.axp.entity;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class Project {

    private UUID projectId;
    private String name;
    // Project-specific configuration data.
    private Map<String, String> configuration;

}
