package org.axp.dao.mapper;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import org.axp.dao.CommentDao;
import org.axp.dao.ProjectDao;
import org.axp.dao.ProjectStatisticDao;
import org.axp.dao.StatusDao;
import org.axp.dao.TicketDao;
import org.axp.dao.UserDao;

@SuppressWarnings("unused")
@Mapper
public interface DaoMapper {

    @DaoFactory
    UserDao userDao();

    @DaoFactory
    ProjectDao projectDao();

    @DaoFactory
    TicketDao ticketDao();

    @DaoFactory
    StatusDao statusDao();

    @DaoFactory
    CommentDao commentDao();

    @DaoFactory
    ProjectStatisticDao projectStatisticDao();

}
