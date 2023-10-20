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
import org.axp.domain.TicketDto;
import org.axp.service.TicketService;

import java.util.List;
import java.util.Objects;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {

    @Inject
    TicketService service;

    @GET
    public List<TicketDto> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/projectId/{projectId}")
    public List<TicketDto> getAllForProjectId(String projectId) {
        List<TicketDto> allForProjectId = service.getAllForProjectId(projectId);
        return allForProjectId;
    }

    @GET
    @Path("/projectId/{projectId}/ticketId/{ticketId}")
    public TicketDto getTicketByIds(String projectId, Integer ticketId) {
        return service.getTicketByIds(projectId, ticketId);
    }

    @POST
    public void add(@Valid TicketDto dto) {
        var latestForProject = service.findLatestForProject(dto.project().id());
        var newId = Objects.isNull(latestForProject) ? 1 : latestForProject.id() + 1;
        var ticketDto = service.copyWithNewId(dto, newId, true);
        var tried = 0;
        while (!service.save(ticketDto) && tried < 100) {
            System.out.println("tried: " + tried + ", dto: " + dto);
            latestForProject = service.findLatestForProject(dto.project().id());
            newId = Objects.isNull(latestForProject) ? 1 : latestForProject.id() + 1;
            ticketDto = service.copyWithNewId(dto, newId, true);
            tried++;
        }
    }

    @PUT
    public void update(TicketDto dto) {
        service.update(dto);
    }

    @DELETE
    public void delete(TicketDto dto) {
        service.delete(dto);
    }

}
