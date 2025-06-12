package com.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.model.User;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void save(User user) {
        if (user != null) {
            entityManager.persist(user);
        }
    }
}