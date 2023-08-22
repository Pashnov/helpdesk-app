package org.axp.rest;

import lombok.Data;

@Data
public class UserDto {

    private final String id;
    private final String username;
    private final String email;
    private final String role; // CUSTOMER, AGENT, ADMIN, SUPERUSER;

}
