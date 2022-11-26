package com.mariesto.buckpal.account.application.service;

import org.springframework.stereotype.Service;
import com.mariesto.buckpal.account.application.port.in.createaccount.CreateAccountCommand;
import com.mariesto.buckpal.account.application.port.in.createaccount.CreateAccountUseCase;
import com.mariesto.buckpal.account.application.port.out.CreateAccountPort;
import com.mariesto.buckpal.account.domain.Account;
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
