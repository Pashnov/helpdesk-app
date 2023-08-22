package org.axp.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import org.axp.entity.TicketStatus;

import static com.datastax.oss.driver.api.mapper.entity.saving.NullSavingStrategy.SET_TO_NULL;

@Dao
public interface StatusDao {

    @Select
    PagingIterable<TicketStatus> findAll();

    @Select
    TicketStatus findById(int id);

    @Update(nullSavingStrategy = SET_TO_NULL, ifExists = true)
    boolean update(TicketStatus ticket);

    @Insert(ifNotExists = true)
    boolean save(TicketStatus status);

    @Delete(ifExists = true)
    boolean delete(TicketStatus status);

}
