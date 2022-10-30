package account.application.port.in.createaccount;

import javax.validation.constraints.NotNull;
import account.domain.Money;
import common.SelfValidating;
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
