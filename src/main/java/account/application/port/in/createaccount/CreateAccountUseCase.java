package account.application.port.in.createaccount;

import account.domain.AccountRequest;

public interface CreateAccountUseCase {

    boolean createAccount(CreateAccountCommand command);

}
