package org.example.services.user.repository;

import org.example.services.user.UserDTO;
import org.example.services.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<UserDTO> findByEventId(Long eventId);

}
