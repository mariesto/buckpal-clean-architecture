package application.account.application.port.in.fetchaccount;

import application.account.domain.Account;
import application.account.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(Account.AccountId accountId);

}
