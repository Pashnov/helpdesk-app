package org.axp.service;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.internal.core.PagingIterableWrapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.axp.dao.UserDao;
import org.axp.entity.User;
import org.axp.transformer.UserTransformer;
import org.axp.rest.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;

    @Inject
    UserTransformer userTransformer;

    public void save(UserDto user) {
        userDao.save(userTransformer.transform(user));
    }

    public List<UserDto> getAll() {
//        return userDao.findAll().stream().map(userTransformer::transform).collect(Collectors.toList());
        return userDao.findAll().all().stream().map(userTransformer::transform).collect(Collectors.toList());
    }

    public void update(UserDto user) {
        userDao.update(userTransformer.transform(user));
    }

    public boolean delete(UserDto user) {
        return userDao.delete(userTransformer.transform(user));
    }

//    @ApplicationScoped
//    public static class UserDao {
//
//        List<User> users = new ArrayList<>();
//
//        public void update(User user) {
//            System.out.println("update user: " + user);
//        }
//
//        public List<User> findAll() {
//            List<User> users1 = new ArrayList<>(List.of(
//                    new User(UUID.randomUUID(), "username1", "email1", User.Role.ADMIN),
//                    new User(UUID.randomUUID(), "username2", "email2", User.Role.ADMIN)
//            ));
//            users1.addAll(users);
//            return users1;
//        }
//
//        public User findById(UUID userId) {
//            return new User(userId, "username", "email", User.Role.ADMIN);
//        }
//
//        public void save(User user) {
//            System.out.println("save user: " + user);
//            users.add(user);
//        }
//
//        public boolean delete(User user) {
//            System.out.println("delete user: " + user);
//            return true;
//        }
//    }

}
