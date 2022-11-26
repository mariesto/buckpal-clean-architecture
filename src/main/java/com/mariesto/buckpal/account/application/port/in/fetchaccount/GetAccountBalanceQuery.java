package com.mariesto.buckpal.account.application.port.in.fetchaccount;

import com.mariesto.buckpal.account.domain.Account;
import com.mariesto.buckpal.account.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(Account.AccountId accountId);

}
