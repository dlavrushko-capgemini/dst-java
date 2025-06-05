package dasniko.soteria;

import dasniko.soteria.entity.Account;
import dasniko.soteria.entity.AccountService;
import dasniko.soteria.entity.TokenService;

import jakarta.inject.Inject;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.credential.RememberMeCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.RememberMeIdentityStore;
import java.util.Optional;
import java.util.Set;

public class DemoRememberMeIdentityStore implements RememberMeIdentityStore {

    @Inject
    private AccountService accountService;
    @Inject
    private TokenService tokenService;

    @Override
    public CredentialValidationResult validate(RememberMeCredential credential) {
        Optional<Account> account = accountService.getByLoginToken(credential.getToken());

        return account.map(a -> new CredentialValidationResult(new CallerPrincipal(a.getUsername())))
            .orElse(CredentialValidationResult.INVALID_RESULT);
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
        return tokenService.generate(callerPrincipal.getName());
    }

    @Override
    public void removeLoginToken(String token) {
        tokenService.remove(token);
    }
}