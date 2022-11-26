package com.mariesto.buckpal.account.adapter.in.web;

import static com.mariesto.buckpal.account.domain.Account.AccountId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mariesto.buckpal.account.application.port.in.sendmoney.SendMoneyCommand;
import com.mariesto.buckpal.account.application.port.in.sendmoney.SendMoneyUseCase;
import com.mariesto.buckpal.account.domain.Money;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SendMoneyController {
    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping ("/accounts/money/send/{source_account_id}/{destination_account_id}/{amount}")
    void sendMoney(@PathVariable ("source_account_id") Integer sourceAccountId, @PathVariable ("destination_account_id") Integer destinationAccountId,
            @PathVariable ("amount") Long amount) {
        SendMoneyCommand command = new SendMoneyCommand(new AccountId(sourceAccountId), new AccountId(destinationAccountId), Money.of(amount));

        sendMoneyUseCase.sendMoney(command);
    }

}
