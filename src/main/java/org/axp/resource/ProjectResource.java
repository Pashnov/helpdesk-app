package org.axp.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.axp.rest.ProjectDto;
import org.axp.service.ProjectService;

import java.util.List;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {

    @Inject
    ProjectService projectService;

    @GET
    public List<ProjectDto> getAll() {
        return projectService.getAll();
    }

    @POST
    public void add(@Valid ProjectDto projectDto) {
        projectService.save(projectDto);
    }

    @PUT
    public void update(ProjectDto projectDto) {
        projectService.update(projectDto);
    }

    @DELETE
    public void delete(ProjectDto projectDto) {
        projectService.delete(projectDto);
    }

}
