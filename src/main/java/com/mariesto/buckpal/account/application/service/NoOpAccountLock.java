package com.mariesto.buckpal.account.application.service;

import org.springframework.stereotype.Component;
import com.mariesto.buckpal.account.application.port.out.AccountLock;
import com.mariesto.buckpal.account.domain.Account;

@Component
public class NoOpAccountLock implements AccountLock {
    @Override
    public void lockAccount(Account.AccountId accountId) {

    }

    @Override
    public void releaseAccount(Account.AccountId accountId) {

    }
}
