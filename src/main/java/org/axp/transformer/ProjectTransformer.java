package org.axp.transformer;

import jakarta.enterprise.context.ApplicationScoped;
import org.axp.entity.Project;
import org.axp.domain.ProjectDto;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class ProjectTransformer {

    public ProjectDto transform(Project project) {
        return new ProjectDto(project.id().toUpperCase(), project.name());
    }

    public Project transform(ProjectDto dto) {
        return new Project(dto.id().toUpperCase(), dto.name());
    }

    public CompletionStage<ProjectDto> transform(CompletionStage<Project> asyncProject) {
        return asyncProject.thenApply(this::transform);
    }
}
