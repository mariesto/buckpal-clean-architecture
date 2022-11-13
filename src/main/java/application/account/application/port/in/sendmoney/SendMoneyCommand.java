package application.account.application.port.in.sendmoney;

import javax.validation.constraints.NotNull;
import application.account.domain.Account.AccountId;
import application.account.domain.Money;
import application.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode (callSuper = false)
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {
    @NotNull
    private final AccountId sourceAccountId;

    @NotNull
    private final AccountId destinationAccountId;

    @NotNull
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountId, AccountId destinationAccountId, Money money) {
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.money = money;
        this.validateSelf();
    }
}
