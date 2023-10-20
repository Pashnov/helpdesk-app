package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import org.axp.domain.CommentDto;
import org.axp.domain.UserDto;
import org.axp.entity.Comment;
import org.axp.service.UserService;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.locks.StampedLock;

import static java.util.concurrent.TimeUnit.SECONDS;

@ApplicationScoped
public class CommentTransformer {

    private final StampedLock stampedLock = new StampedLock();

    @Inject
    UserService userService;

    @SneakyThrows
    public CommentDto transform(Comment comment) {
        if (Objects.isNull(comment)) {
            return null;
        }
        UserDto user = userService.getById(comment.userId()).toCompletableFuture().get(5, SECONDS);
        return new CommentDto(comment.projectId(), comment.ticketId(), comment.dateSubmitted(), comment.content(), user);
    }

    public Comment transform(CommentDto dto) {
        UUID userId = Optional.ofNullable(dto.user())
                .map(UserDto::id)
                .map(UUID::fromString)
                .orElse(null);
        LocalDateTime dateSubmitted = dto.dateSubmitted();
        if (Objects.isNull(dto.dateSubmitted())) {
            dateSubmitted = getNowDateTime();
        }

        return new Comment(dto.projectId(), dto.ticketId(), dateSubmitted, dto.content(), userId);
    }

    private LocalDateTime getNowDateTime() {
        long stamp = stampedLock.tryOptimisticRead();
        var now = LocalDateTime.now();
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.writeLock();
            try {
                now = LocalDateTime.now();
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        }
        return now;
    }

}
