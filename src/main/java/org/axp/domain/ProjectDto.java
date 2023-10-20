package org.axp.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectDto (
    @Size(message = "Project id should be greater 2 symbols and less 6", min = 3, max = 5)
    String id,

    @Size(message = "Name should have at lease 5 symbols", min = 5)
    @NotBlank(message="Name may not be blank")
    String name
) {}
