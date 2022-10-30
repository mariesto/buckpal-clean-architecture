package account.application.port.in.fetchaccount;

import java.util.List;
import account.domain.AccountRequest;

public interface ListAccountsQuery {

    List<AccountRequest> listAccounts();

    AccountRequest fetchAccountByIdAndDateRange(Long accountId);
}
