package org.axp.resource;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.axp.rest.TicketStatusDto;
import org.axp.service.TicketStatusService;

import java.util.List;

@Path("/statuses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketStatusResource {

    @Inject
    TicketStatusService service;

    @GET
    public List<TicketStatusDto> getAll() {
        return service.getAll();
    }

    @POST
    public void add(@Valid TicketStatusDto dto) {
        if (!service.save(dto)) {
//            throw new ConstraintViolationException(Set.of(getValidation(dto)));
            throw new ConstraintDeclarationException("Such status already exists");
        }
    }

    @PUT
    public void update(TicketStatusDto dto) {
        if (!service.update(dto)) {
            throw new ConstraintDeclarationException("Such status doesn't exist");
        }
    }

    @DELETE
    public void delete(TicketStatusDto dto) {
        service.delete(dto);
    }

//    private ConstraintViolation<TicketStatusDto> getValidation(TicketStatusDto dto) {
//        return ConstraintViolationImpl.forBeanValidation("Such status exists", null, null, "interpolated message", TicketStatusDto.class, dto, null, dto.getId(), null, getConstraintDescriptor(), null);
//    }
//
//    private static ConstraintDescriptor<?> getConstraintDescriptor() {
//        return new ConstraintDescriptorImpl<>(new);
//    }

}
