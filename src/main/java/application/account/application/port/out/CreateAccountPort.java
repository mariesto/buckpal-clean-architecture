package application.account.application.port.out;

import application.account.domain.Account;

public interface CreateAccountPort {
    Account createAccount(Account account);
}
