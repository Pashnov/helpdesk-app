package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.UserDao;
import org.axp.rest.UserDto;
import org.axp.transformer.UserTransformer;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao dao;

    @Inject
    UserTransformer transformer;

    public void save(UserDto user) {
        dao.save(transformer.transform(user));
    }

    public List<UserDto> getAll() {
        return dao.findAll().all().stream().map(transformer::transform).collect(Collectors.toList());
    }

    public CompletionStage<UserDto> getById(UUID id) {
        return transformer.transform(dao.findByIdAsync(id));
    }

    public void update(UserDto user) {
        dao.update(transformer.transform(user));
    }

    public boolean delete(UserDto user) {
        return dao.delete(transformer.transform(user));
    }

}
