package com.mariesto.buckpal.account.application.port.in.sendmoney;

import javax.validation.constraints.NotNull;
import com.mariesto.buckpal.account.domain.Account.AccountId;
import com.mariesto.buckpal.account.domain.Money;
import com.mariesto.buckpal.common.SelfValidating;
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
