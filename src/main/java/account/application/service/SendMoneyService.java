package account.application.service;

import org.springframework.transaction.annotation.Transactional;
import account.application.port.in.SendMoneyCommand;
import account.application.port.in.SendMoneyUseCase;
import account.application.port.out.AccountLock;
import account.application.port.out.LoadAccountPort;
import account.application.port.out.UpdateAccountStatePort;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

    private final AccountLock accountLock;
    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        return false;
    }
}
