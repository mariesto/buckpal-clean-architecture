package application.account.application.port.out;

import application.account.domain.Account;

public interface AccountLock {
    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);
}
