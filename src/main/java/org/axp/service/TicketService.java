package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.TicketDao;
import org.axp.entity.Ticket;
import org.axp.rest.TicketDto;
import org.axp.transformer.TicketTransformer;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TicketService {

    @Inject
    TicketDao dao;

    @Inject
    TicketTransformer transformer;

    public boolean save(TicketDto ticketDto) {
        System.out.println(LocalTime.now() + " save,  ticketDto" + ticketDto);
        return dao.save(transformer.transform(ticketDto));
    }

    public List<TicketDto> getAll() {
        return dao.findAll().all().stream().map(transformer::transform).collect(Collectors.toList());
    }

    public TicketDto findLatestForProject(String projectId) {
        Ticket latestTicket = dao.findByProjectIdLatestTicketByTicketId(projectId);
        return transformer.transform(latestTicket);
    }

    public void update(TicketDto user) {
        dao.update(transformer.transform(user));
    }

    public boolean delete(TicketDto user) {
        return dao.delete(transformer.transform(user));
    }

    public TicketDto copyWithNewId(TicketDto old, int newId, boolean isActive) {
        return new TicketDto(old.getProject(), newId, old.getName(), old.getDescription(),
                isActive, old.getDateSubmitted(), old.getPriority(), old.getCreatedByUser(),
                old.getAssignedToUser(), old.getStatus());
    }

}
