package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.User;
import org.axp.rest.UserDto;

import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class UserTransformer {

    public UserDto transform(User user) {
        return new UserDto(user.getUsername(), user.getEmail(), user.getUserId().toString(), user.getRole().name());
    }

    public User transform(UserDto dto) {
        UUID userId = Objects.nonNull(dto.getUserId()) ? UUID.fromString(dto.getUserId()) : UUID.randomUUID();
        User.Role role = User.Role.findByName(dto.getRole());;
        return new User(userId, dto.getUsername(), dto.getEmail(), role);
    }

}
