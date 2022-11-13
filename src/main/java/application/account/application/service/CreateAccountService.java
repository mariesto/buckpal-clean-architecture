package application.account.application.service;

import org.springframework.stereotype.Service;
import application.account.application.port.in.createaccount.CreateAccountCommand;
import application.account.application.port.in.createaccount.CreateAccountUseCase;
import application.account.application.port.out.CreateAccountPort;
import application.account.domain.Account;
import lombok.RequiredArgsConstructor;

@Service
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
