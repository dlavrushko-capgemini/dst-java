package com.example.service;

import com.example.exception.UsernameAlreadyTakenException;
import com.example.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void createAccount(Account account) {
        if (isUsernameTaken(account.getUsername())) {
            throw new UsernameAlreadyTakenException("Username is already taken.");
        }
        account.setActive(true);
        account.setRole("USER");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        try {
            CompletableFuture.runAsync(() -> entityManager.persist(account))
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    logger.error("Error while creating account: {}", e.getMessage());
                    throw new RuntimeException("Database error occurred.");
                }).join();
        } catch (Exception e) {
            logger.error("Error while creating account: {}", e.getMessage());
            throw e;
        }
    }

    public Account findByUsername(String username) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class);
        query.setParameter("username", username);
        return query.getResultStream().findFirst().orElse(null);
    }

    public boolean authenticate(Account account, String password) {
        return passwordEncoder.matches(password, account.getPassword());
    }

    public boolean verifyPassword(Account account, String password) {
        return passwordEncoder.matches(password, account.getPassword());
    }

    private boolean isUsernameTaken(String username) {
        return findByUsername(username) != null;
    }
}