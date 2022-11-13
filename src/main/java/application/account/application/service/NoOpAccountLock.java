package application.account.application.service;

import org.springframework.stereotype.Component;
import application.account.application.port.out.AccountLock;
import application.account.domain.Account;

@Component
public class NoOpAccountLock implements AccountLock {
    @Override
    public void lockAccount(Account.AccountId accountId) {

    }

    @Override
    public void releaseAccount(Account.AccountId accountId) {

    }
}
