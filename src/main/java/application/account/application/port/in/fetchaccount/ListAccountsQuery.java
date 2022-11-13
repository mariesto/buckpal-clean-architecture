package application.account.application.port.in.fetchaccount;

import application.account.domain.Account;

public interface ListAccountsQuery {
    Account fetchAccountByIdAndDateRange(Integer accountId);
}
