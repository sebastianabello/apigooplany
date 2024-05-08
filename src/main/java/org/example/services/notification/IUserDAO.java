package org.example.services.notification;

import org.example.services.user.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    List<User> findAll();
    Optional<User> findById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
