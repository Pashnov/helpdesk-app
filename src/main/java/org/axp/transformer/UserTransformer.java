package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.User;
import org.axp.rest.UserDto;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class UserTransformer {

    public UserDto transform(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        return new UserDto(user.getId().toString(), user.getUsername(), user.getEmail(), user.getRole().name());
    }

    public User transform(UserDto dto) {
        var userId = Objects.nonNull(dto.getId()) ? UUID.fromString(dto.getId()) : UUID.randomUUID();
        var role = Objects.nonNull(dto.getRole()) ? User.Role.valueOf(dto.getRole()) : null ;
        return new User(userId, dto.getUsername(), dto.getEmail(), role);
    }

    public CompletionStage<UserDto> transform(CompletionStage<User> asyncUser) {
        return asyncUser.thenApply(this::transform);
    }

}
