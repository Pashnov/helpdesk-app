package org.axp.domain;

public record UserDto (
        String id,
        String username,
        String email,
        String role // CUSTOMER, AGENT, ADMIN, SUPERUSER;
) {}
