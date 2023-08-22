package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.ProjectDao;
import org.axp.rest.ProjectDto;
import org.axp.transformer.ProjectTransformer;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProjectService {

    @Inject
    ProjectDao dao;

    @Inject
    ProjectTransformer transformer;

    public void save(ProjectDto projectDto) {
        dao.save(transformer.transform(projectDto));
    }

    public List<ProjectDto> getAll() {
        return dao.findAll().all().stream().map(transformer::transform).collect(Collectors.toList());
    }

    public void update(ProjectDto user) {
        dao.update(transformer.transform(user));
    }

    public boolean delete(ProjectDto user) {
        return dao.delete(transformer.transform(user));
    }

    public CompletionStage<ProjectDto> getById(String projectId) {
        return transformer.transform(dao.findByIdAsync(projectId));
    }
}
