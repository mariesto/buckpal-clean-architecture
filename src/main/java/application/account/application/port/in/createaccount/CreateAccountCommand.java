package application.account.application.port.in.createaccount;

import javax.validation.constraints.NotNull;
import application.account.domain.Money;
import application.common.SelfValidating;
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
