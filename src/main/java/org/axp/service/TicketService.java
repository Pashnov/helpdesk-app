package org.axp.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.TicketDao;
import org.axp.entity.Ticket;
import org.axp.domain.TicketDto;
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
        // todo:: parallel stream
        return dao.findAll().all().stream().map(transformer::transform).collect(Collectors.toList());
    }

    public List<TicketDto> getAllForProjectId(String projectId) {
        // todo:: parallel stream
        List<TicketDto> collect = dao.findAllByProjectId(projectId).all().stream().map(transformer::transform).collect(Collectors.toList());
        return collect;
    }

    public TicketDto getTicketByIds(String projectId, Integer ticketId) {
        Ticket ticket = dao.findById(projectId, ticketId);
        return transformer.transform(ticket);
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
        return new TicketDto(old.project(), newId, old.name(), old.description(),
                isActive, old.dateSubmitted(), old.priority(), old.reporterUser(),
                old.assigneeUser(), old.status());
    }

}
