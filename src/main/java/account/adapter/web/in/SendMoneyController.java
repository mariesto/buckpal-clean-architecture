package account.adapter.web.in;

import static account.domain.Account.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import account.application.port.in.sendmoney.SendMoneyCommand;
import account.application.port.in.sendmoney.SendMoneyUseCase;
import account.domain.Money;
import lombok.RequiredArgsConstructor;

@RestController("/accounts")
@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping ("/send/{source_account_id}/{destination_account_id}/{amount}")
    void sendMoney(@PathVariable ("source_account_id") Long sourceAccountId, @PathVariable("destination_account_id") Long destinationAccountId,
            @PathVariable("amount") Long amount){
        SendMoneyCommand command = new SendMoneyCommand(
                new AccountId(sourceAccountId),
                new AccountId(destinationAccountId),
                Money.of(amount));

        sendMoneyUseCase.sendMoney(command);
    }

}
