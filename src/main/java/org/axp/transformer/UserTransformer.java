package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.User;
import org.axp.domain.UserDto;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class UserTransformer {

    public UserDto transform(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        return new UserDto(user.id().toString(), user.username(), user.email(), user.role().name());
    }

    public User transform(UserDto dto) {
        var userId = Objects.nonNull(dto.id()) ? UUID.fromString(dto.id()) : UUID.randomUUID();
        var role = Objects.nonNull(dto.role()) ? User.Role.valueOf(dto.role()) : null ;
        return new User(userId, dto.username(), dto.email(), role);
    }

    public CompletionStage<UserDto> transform(CompletionStage<User> asyncUser) {
        return asyncUser.thenApply(this::transform);
    }

}
