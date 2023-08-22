package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.Project;
import org.axp.rest.ProjectDto;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class ProjectTransformer {

    public ProjectDto transform(Project project) {
        return new ProjectDto(project.getId(), project.getName());
    }

    public Project transform(ProjectDto dto) {
        return new Project(dto.getId(), dto.getName());
    }

    public CompletionStage<ProjectDto> transform(CompletionStage<Project> asyncProject) {
        return asyncProject.thenApply(this::transform);
    }
}
