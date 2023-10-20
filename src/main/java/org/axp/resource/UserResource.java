package org.axp.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.axp.service.UserService;
import org.axp.domain.UserDto;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @POST
    public void add(UserDto user) {
        userService.save(user);
    }

    @PUT
    public void update(UserDto user) {
        userService.update(user);
    }

    @DELETE
    public void delete(UserDto user) {
        userService.delete(user);
    }

}
