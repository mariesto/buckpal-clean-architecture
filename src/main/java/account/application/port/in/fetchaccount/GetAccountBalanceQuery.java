package account.application.port.in.fetchaccount;

import account.domain.Account;
import account.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(Account.AccountId accountId);

}
