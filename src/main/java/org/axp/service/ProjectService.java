package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.ProjectDao;
import org.axp.domain.ProjectDto;
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
    public void update(ProjectDto projectDto) {
        dao.update(transformer.transform(projectDto));
    }
    public boolean delete(ProjectDto projectDto) {
        return dao.delete(transformer.transform(projectDto));
    }
    public CompletionStage<ProjectDto> getById(String projectId) {
        return transformer.transform(dao.findByIdAsync(projectId));
    }
}
