package account.application.port.out;

import java.time.LocalDateTime;
import account.domain.Account;

public interface LoadAccountPort {
    Account loadAccount(Account.AccountId accountId, LocalDateTime now);
}
