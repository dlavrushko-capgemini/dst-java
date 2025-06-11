package com.example.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.example.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    public Account getAccountByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}