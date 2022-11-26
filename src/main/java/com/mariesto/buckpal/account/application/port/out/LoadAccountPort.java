package com.mariesto.buckpal.account.application.port.out;

import java.time.LocalDateTime;
import com.mariesto.buckpal.account.domain.Account;

public interface LoadAccountPort {
    Account loadAccount(Account.AccountId accountId, LocalDateTime now);
}
