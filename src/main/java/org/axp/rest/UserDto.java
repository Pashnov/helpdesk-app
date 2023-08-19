package org.axp.rest;

import lombok.Data;

@Data
public class UserDto {

    private final String username;
    private final String email;
    private final String userId;
    private final String role;

}
