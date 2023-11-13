package org.axp.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import org.axp.entity.Project;

import java.util.concurrent.CompletionStage;

@Dao
public interface ProjectDao {

    @Update
    void update(Project project);
    @Select
    PagingIterable<Project> findAll();
    @Select
    Project findById(String projectId);
    @Select
    CompletionStage<Project> findByIdAsync(String projectId);
    @Insert
    void save(Project project);
    @Delete(ifExists = true)
    boolean delete(Project project);

}
