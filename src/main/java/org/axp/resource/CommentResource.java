package org.axp.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.axp.domain.CommentDto;
import org.axp.service.CommentService;

import java.util.List;

@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Inject
    CommentService commentService;

    @GET
    @Path("/projectId/{projectId}/ticketId/{ticketId}")
    public List<CommentDto> getAllByIds(String projectId, int ticketId) {
        return commentService.findAllByIds(projectId, ticketId);
    }

    @POST
    public void add(CommentDto comment) {
        commentService.save(comment);
    }

    @DELETE
    public void delete(CommentDto comment) {
        commentService.delete(comment);
    }

}
