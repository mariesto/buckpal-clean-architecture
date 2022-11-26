package com.mariesto.buckpal.account.application.port.in.createaccount;

import javax.validation.constraints.NotNull;
import com.mariesto.buckpal.account.domain.Money;
import com.mariesto.buckpal.common.SelfValidating;
import lombok.Value;

@Value
public class CreateAccountCommand extends SelfValidating<CreateAccountCommand> {
    @NotNull
    private final Money money;

    public CreateAccountCommand(Money money) {
        this.money = money;
        this.validateSelf();
    }
}
