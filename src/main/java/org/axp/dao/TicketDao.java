package org.axp.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Query;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import org.axp.entity.Ticket;

import java.time.LocalDateTime;

@Dao
public interface TicketDao {

    @Update
    void update(Ticket ticket);

    @Select
    PagingIterable<Ticket> findAll();

    @Select
    PagingIterable<Ticket> findAllByProjectId(String projectId);

    @Select(orderBy = "ticket_id DESC")
    Ticket findByProjectIdLatestTicketByTicketId(String projectId);

    @Select(customWhereClause = "project_id = :projectId AND date_submitted >= :start AND date_submitted <= :end", allowFiltering = true)
    PagingIterable<Ticket> findByProjectIdInAndRangeOfDateSubmitted(String projectId, @CqlName("start") LocalDateTime start, @CqlName("end") LocalDateTime end);

    @Select
    Ticket findById(String projectId, int ticketId);

    @Insert(ifNotExists = true)
    boolean save(Ticket ticket);

    @Delete(ifExists = true)
    boolean delete(Ticket ticket);

}
