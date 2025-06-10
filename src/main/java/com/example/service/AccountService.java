package com.example.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Stateless
public class AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Account> getByUsername(String username) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class);
        query.setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }

    public Optional<Account> getByEmail(@Email String email) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }

    public Optional<Account> getByLoginToken(String loginToken) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.loginToken = :loginToken", Account.class);
        query.setParameter("loginToken", loginToken);
        return query.getResultList().stream().findFirst();
    }

    public Account getByUsernameAndPassword(String username, String password) {
        String hashedPassword = hashPassword(password);
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password", Account.class);
        query.setParameter("username", username);
        query.setParameter("password", hashedPassword);
        return query.getResultList().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
    }

    public Account save(@NotBlank String username, @NotBlank String password, @Email String email) {
        if (getByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (getByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        String hashedPassword = hashPassword(password);
        Account account = new Account(username, hashedPassword, email);
        entityManager.persist(account);
        return account;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}