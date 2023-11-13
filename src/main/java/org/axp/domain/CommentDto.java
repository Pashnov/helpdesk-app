package org.axp.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record CommentDto (
        String projectId,
        int ticketId,
        @Null(message = "date will generated automatically")
        LocalDateTime dateSubmitted,
        @NotBlank(message = "Comment content must not be empty")
        String content,
        UserDto user
) {}
