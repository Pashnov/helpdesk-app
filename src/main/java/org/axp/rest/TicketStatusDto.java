package org.axp.rest;

import lombok.Data;

@Data
public class TicketStatusDto {

    private final int id;
    private final String name;
    private final String description;

}
