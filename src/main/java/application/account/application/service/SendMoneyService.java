package application.account.application.service;

import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import application.account.application.port.in.sendmoney.SendMoneyCommand;
import application.account.application.port.in.sendmoney.SendMoneyUseCase;
import application.account.application.port.out.AccountLock;
import application.account.application.port.out.LoadAccountPort;
import application.account.application.port.out.UpdateAccountStatePort;
import application.account.domain.Account;
import application.account.domain.Account.AccountId;

@Transactional
public class SendMoneyService implements SendMoneyUseCase {
    private final AccountLock accountLock;

    private final LoadAccountPort loadAccountPort;

    private final UpdateAccountStatePort updateAccountStatePort;

    public SendMoneyService(AccountLock accountLock, LoadAccountPort loadAccountPort, UpdateAccountStatePort updateAccountStatePort) {
        this.accountLock = accountLock;
        this.loadAccountPort = loadAccountPort;
        this.updateAccountStatePort = updateAccountStatePort;
    }

    @Override
    public boolean sendMoney(SendMoneyCommand command) {

        LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

        Account sourceAccount = loadAccountPort.loadAccount(command.getSourceAccountId(), baselineDate);
        Account destinationAccount = loadAccountPort.loadAccount(command.getDestinationAccountId(), baselineDate);

        AccountId sourceAccountId = sourceAccount.getId().orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
        AccountId destinationAccountId = destinationAccount.getId().orElseThrow(
                () -> new IllegalStateException("expected target account ID not to be empty"));

        accountLock.lockAccount(sourceAccountId);
        if (!sourceAccount.withdraw(command.getMoney(), destinationAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            return false;
        }

        accountLock.lockAccount(destinationAccountId);
        if (!destinationAccount.deposit(command.getMoney(), sourceAccountId)) {
            accountLock.releaseAccount(sourceAccountId);
            accountLock.releaseAccount(destinationAccountId);
            return false;
        }

        updateAccountStatePort.updateActivities(sourceAccount);
        updateAccountStatePort.updateActivities(destinationAccount);

        accountLock.releaseAccount(sourceAccountId);
        accountLock.releaseAccount(destinationAccountId);
        return true;
    }
}
