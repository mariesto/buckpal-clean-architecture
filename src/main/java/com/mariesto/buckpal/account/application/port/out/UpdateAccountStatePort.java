package com.mariesto.buckpal.account.application.port.out;

import com.mariesto.buckpal.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account sourceAccount);
}
