package com.mariesto.buckpal.account.application.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.mariesto.buckpal.account.application.port.in.fetchaccount.GetAccountBalanceQuery;
import com.mariesto.buckpal.account.application.port.out.LoadAccountPort;
import com.mariesto.buckpal.account.domain.Account;
import com.mariesto.buckpal.account.domain.Money;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now().minusDays(7)).calculateBalance();
    }
}
