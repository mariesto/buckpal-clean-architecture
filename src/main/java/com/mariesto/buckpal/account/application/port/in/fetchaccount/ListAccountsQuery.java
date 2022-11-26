package com.mariesto.buckpal.account.application.port.in.fetchaccount;

import com.mariesto.buckpal.account.domain.Account;

public interface ListAccountsQuery {
    Account fetchAccountByIdAndDateRange(Integer accountId);
}
