package dasniko.soteria.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.MONTHS;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@NamedQueries({
    @NamedQuery(name = Token.REMOVE_TOKEN, query = "delete from Token t where t.tokenHash = :tokenHash"),
})
public class Token {

    public static final String REMOVE_TOKEN = "removeToken";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tokenHash;
    private Instant created;
    private Instant expiration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @PrePersist
    public void prePersist() {
        created = Instant.now();
        if (expiration == null) {
            expiration = created.plus(1, MONTHS);
        }
    }
}