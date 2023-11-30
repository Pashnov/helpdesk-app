package org.axp.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import org.axp.entity.Comment;

import java.util.Optional;

@Dao
public interface CommentDao {

    @Select
    PagingIterable<Comment> findAllByIds(String projectId, int ticketId);

    @Select
    Optional<Comment> findLastComment(String projectId, int ticketId);

    @Insert
    void save(Comment comment);

    @Delete(ifExists = true)
    boolean delete(Comment comment);

}
