package account.application.port.out;

import account.domain.Account;

public interface CreateAccountPort {
    Account createAccount(Account account);
}
