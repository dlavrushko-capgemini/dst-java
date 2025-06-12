package dasniko.soteria;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class SecurityException extends RuntimeException {

    public SecurityException(Reason reason) {
        super(reason.name());
    }

    public enum Reason {
        ACCOUNT_NOT_VERIFIED,
        INVALID_CREDENTIALS,
        INVALID_PASSWORD,
        INVALID_USERNAME
    }

}