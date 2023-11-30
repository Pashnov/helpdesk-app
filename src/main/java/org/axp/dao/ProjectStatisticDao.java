package org.axp.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;
import org.axp.entity.Project;
import org.axp.entity.ProjectStatistic;
import org.axp.entity.User;

import java.util.UUID;
import java.util.concurrent.CompletionStage;

@Dao
public interface ProjectStatisticDao {

    @Update
    void update(ProjectStatistic statistic);

    @Select
    PagingIterable<ProjectStatistic> findAll();

    @Select
    PagingIterable<ProjectStatistic> findAllByProjectId(String projectId);

    @Insert
    void save(ProjectStatistic statistic);

}
