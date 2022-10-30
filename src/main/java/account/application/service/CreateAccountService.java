package account.application.service;

import account.application.port.in.createaccount.CreateAccountCommand;
import account.application.port.in.createaccount.CreateAccountUseCase;
import account.application.port.out.CreateAccountPort;
import account.domain.Account;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private final CreateAccountPort createAccountPort;

    @Override
    public boolean createAccount(CreateAccountCommand command) {
        Account account = createAccountPort.createAccount(Account.withoutId(command.getMoney(), null));
        if (account.getId().isEmpty()) {
            throw new IllegalStateException("Failed to create account");
        }
        return true;
    }
}
