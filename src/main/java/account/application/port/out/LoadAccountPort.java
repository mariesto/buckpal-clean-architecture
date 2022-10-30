package account.application.port.out;

import java.time.LocalDateTime;
import java.util.List;
import account.domain.Account;

public interface LoadAccountPort {
    Account loadAccount(Account.AccountId accountId, LocalDateTime now);

    List<Account> loadAllAccounts();

}
