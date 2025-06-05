package dasniko.soteria.entity;

import dasniko.soteria.SecurityException;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.Instant;
import java.util.UUID;

import static dasniko.soteria.SecurityException.Reason.INVALID_USERNAME;
import static java.time.temporal.ChronoUnit.DAYS;

@Stateless
public class TokenService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountService accountService;

    public String generate(final String username) {
        var rawToken = UUID.randomUUID().toString();
        var expiration = Instant.now().plus(14, DAYS);

        save(rawToken, username, expiration);

        return rawToken;
    }

    private void save(final String rawToken, final String username, final Instant expiration) {
        var account = accountService.getByUsername(username)
            .orElseThrow(() -> new SecurityException(INVALID_USERNAME));

        var token = new Token();
        token.setTokenHash(rawToken);
        token.setExpiration(expiration);

        account.addToken(token);

        em.merge(account);
    }

    public void remove(String token) {
        em.createNamedQuery(Token.REMOVE_TOKEN)
            .setParameter("tokenHash", token).executeUpdate();
    }
}