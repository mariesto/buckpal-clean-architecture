package com.mariesto.buckpal.account.application.port.out;

import com.mariesto.buckpal.account.domain.Account;

public interface CreateAccountPort {
    Account createAccount(Account account);
}
