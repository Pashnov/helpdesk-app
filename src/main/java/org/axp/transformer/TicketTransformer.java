package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.axp.entity.Ticket;
import org.axp.rest.ProjectDto;
import org.axp.rest.TicketDto;
import org.axp.rest.TicketStatusDto;
import org.axp.rest.UserDto;
import org.axp.service.ProjectService;
import org.axp.service.TicketStatusService;
import org.axp.service.UserService;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.TimeUnit.SECONDS;

@ApplicationScoped
public class TicketTransformer {

    @Inject
    UserService userService;

    @Inject
    ProjectService projectService;

    @Inject
    TicketStatusService statusService;

    @SneakyThrows
    public TicketDto transform(Ticket ticket) {
        if (Objects.isNull(ticket)) {
            return null;
        }
        CompletionStage<ProjectDto> projectAsync = projectService.getById(ticket.getProjectId());
        CompletionStage<UserDto> createdUserAsync = userService.getById(ticket.getCreatedByUser());
        CompletionStage<UserDto> assignedUserAsync = userService.getById(ticket.getAssignedToUser());
        TicketStatusDto statusDto = statusService.getById(ticket.getStatusId());

        ProjectDto projectDto = projectAsync.toCompletableFuture().get(10, SECONDS);
        UserDto createdUserDto = createdUserAsync.toCompletableFuture().get(10, SECONDS);
        UserDto assignedUserDto = assignedUserAsync.toCompletableFuture().get(10, SECONDS);

        return new TicketDto(projectDto, ticket.getId(), ticket.getName(),
                ticket.getDescription(), ticket.isActive(), ticket.getDateSubmitted(),
                ticket.getPriority().name(), createdUserDto, assignedUserDto, statusDto);
    }

    public Ticket transform(TicketDto dto) {
        Ticket.Priority priority = Ticket.Priority.valueOf(dto.getPriority());
        UUID createdByUserId = UUID.fromString(dto.getCreatedByUser().getId());
        UUID assignedToUserId = UUID.fromString(dto.getAssignedToUser().getId());
        LocalDateTime ldt = Objects.requireNonNullElse(dto.getDateSubmitted(), LocalDateTime.now());

        return new Ticket(dto.getProject().getId(), dto.getId(), dto.getName(),
                            dto.getDescription(), dto.isActive(), ldt, priority, createdByUserId,
                                assignedToUserId, dto.getStatus().getId());
    }

}
