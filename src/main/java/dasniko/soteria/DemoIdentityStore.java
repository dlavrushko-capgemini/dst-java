package dasniko.soteria;

import dasniko.soteria.entity.Account;
import dasniko.soteria.entity.AccountService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.CallerOnlyCredential;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import static dasniko.soteria.SecurityException.Reason.ACCOUNT_NOT_VERIFIED;
import static dasniko.soteria.SecurityException.Reason.INVALID_CREDENTIALS;

@ApplicationScoped
public class DemoIdentityStore implements IdentityStore {

    @Inject
    private AccountService accountService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        try {
            if (credential instanceof UsernamePasswordCredential upCredential) {
                var username = upCredential.getCaller();
                var password = upCredential.getPasswordAsString();
                return validate(accountService.getByUsernameAndPassword(username, password));
            }

            if (credential instanceof CallerOnlyCredential callerCredential) {
                var username = callerCredential.getCaller();
                return validate(accountService.getByUsername(username)
                    .orElseThrow(() -> new SecurityException(INVALID_CREDENTIALS)));
            }
        } catch (SecurityException e) {
            return CredentialValidationResult.INVALID_RESULT;
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }

    private CredentialValidationResult validate(Account account) {
        if (!account.isActive()) {
            throw new SecurityException(ACCOUNT_NOT_VERIFIED);
        }
        return new CredentialValidationResult(account.getUsername(), account.getRoles());
    }
}