package org.axp.transformer;

import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.axp.entity.Ticket;
import org.axp.domain.ProjectDto;
import org.axp.domain.TicketDto;
import org.axp.domain.TicketStatusDto;
import org.axp.domain.UserDto;
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
        CompletionStage<ProjectDto> projectAsync = projectService.getById(ticket.projectId());
        CompletionStage<UserDto> createdUserAsync = userService.getById(ticket.reporterUser());
        CompletionStage<UserDto> assignedUserAsync = userService.getById(ticket.assigneeUser());
        TicketStatusDto statusDto = statusService.getById(ticket.statusId());

        ProjectDto projectDto = projectAsync.toCompletableFuture().get(5, SECONDS);
        UserDto createdUserDto = createdUserAsync.toCompletableFuture().get(5, SECONDS);
        UserDto assignedUserDto = assignedUserAsync.toCompletableFuture().get(5, SECONDS);

        return new TicketDto(projectDto, ticket.id(), ticket.name(),
                ticket.description(), ticket.active(), ticket.dateSubmitted(),
                ticket.priority().name(), createdUserDto, assignedUserDto, statusDto);
    }

    public Ticket transform(TicketDto dto) {
        Ticket.Priority priority = Ticket.Priority.valueOf(dto.priority());
        UUID createdByUserId = UUID.fromString(dto.reporterUser().id());
        UUID assignedToUserId = Objects.isNull(dto.assigneeUser()) || StringUtil.isNullOrEmpty(dto.assigneeUser().id())
                                                ? null : UUID.fromString(dto.assigneeUser().id());
        LocalDateTime ldt = Objects.requireNonNullElse(dto.dateSubmitted(), LocalDateTime.now());

        return new Ticket(dto.project().id(), dto.id(), dto.name(),
                            dto.description(), dto.active(), ldt, priority, createdByUserId,
                                assignedToUserId, dto.status().id());
    }

}
