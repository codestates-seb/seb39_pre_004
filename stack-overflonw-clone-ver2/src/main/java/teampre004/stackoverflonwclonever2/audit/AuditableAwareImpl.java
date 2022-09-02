package teampre004.stackoverflonwclonever2.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import teampre004.stackoverflonwclonever2.domain.account.Account;

import java.util.Optional;

@Service
public class AuditableAwareImpl implements AuditorAware<Account> {

    @Override
    public Optional<Account> getCurrentAuditor() {

        return Optional.empty();
    }
}
