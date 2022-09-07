package teampre004.stackoverflonwclonever2.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teampre004.stackoverflonwclonever2.domain.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}