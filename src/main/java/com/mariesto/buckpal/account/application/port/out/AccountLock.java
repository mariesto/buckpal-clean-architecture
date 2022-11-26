package com.mariesto.buckpal.account.application.port.out;

import com.mariesto.buckpal.account.domain.Account;

public interface AccountLock {
    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);
}
