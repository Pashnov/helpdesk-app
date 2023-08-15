package org.axp.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Project {

    private int projectId;
    private String name;
    private Map<String, String> configuration; // Project-specific configuration data.

}
