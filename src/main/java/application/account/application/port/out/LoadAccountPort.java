package application.account.application.port.out;

import java.time.LocalDateTime;
import application.account.domain.Account;

public interface LoadAccountPort {
    Account loadAccount(Account.AccountId accountId, LocalDateTime now);
}
