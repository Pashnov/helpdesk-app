package org.axp.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import org.axp.entity.User;

import java.util.UUID;

@Dao
public interface UserDao {

    @Update
    void update(User User);

    @Select
    PagingIterable<User> findAll();

    @Select
    User findById(UUID userId);

    @Insert
    void save(User user);

    @Delete(ifExists = true)
    boolean delete(User user);

}
