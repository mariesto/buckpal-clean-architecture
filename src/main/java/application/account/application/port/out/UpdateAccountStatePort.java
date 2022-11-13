package application.account.application.port.out;

import application.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account sourceAccount);
}
