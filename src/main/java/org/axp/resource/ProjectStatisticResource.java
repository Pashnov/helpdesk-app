package org.axp.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.axp.domain.ProjectStatisticDto;
import org.axp.service.ProjectStatisticService;

import java.util.List;

@Path("/statistic")
public class ProjectStatisticResource {

    @Inject
    ProjectStatisticService projectStatisticService;

    @GET
    @Path("/projectId/{projectId}")
    public List<ProjectStatisticDto> getStatisticForProject(String projectId) {
        return projectStatisticService.getStatisticForProject(projectId);
    }

    @POST
    @Path("/calculate")
    public Response calculateStatistic(@QueryParam("projectId") String projectId) {
        projectStatisticService.calculateStatisticsForCurrentWeek(projectId, true);
        return Response.ok().build();
    }

    @POST
    @Path("/calculate/week/{week}")
    public Response calculateStatistic(int week, @QueryParam("projectId") String projectId) {
        projectStatisticService.calculateStatisticsForCurrentWeek(projectId, true);
        projectStatisticService.calculateStatisticsForCurrentWeek(projectId, week, true);
        return Response.ok().build();
    }
}
