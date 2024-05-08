package org.example.persistence.dao.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.persistence.dao.interfaces.IUserDAO;
import org.example.services.user.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Es igual que en repository, IUserRepository solo que aquí se hace manualmente.

@Repository
public class UserDAOImpl implements IUserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(this.em.find(User.class, id));
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        this.em.persist(user);
        this.em.flush();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.em.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        this.em.remove(user);
    }
}
