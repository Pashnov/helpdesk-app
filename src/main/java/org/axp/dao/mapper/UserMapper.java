package org.axp.dao.mapper;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import org.axp.dao.UserDao;

@Mapper
public interface UserMapper {

    @DaoFactory
    UserDao userDao();

}
