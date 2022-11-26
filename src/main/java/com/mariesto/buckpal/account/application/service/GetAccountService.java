package com.mariesto.buckpal.account.application.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.mariesto.buckpal.account.application.port.in.fetchaccount.ListAccountsQuery;
import com.mariesto.buckpal.account.application.port.out.LoadAccountPort;
import com.mariesto.buckpal.account.domain.Account;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetAccountService implements ListAccountsQuery {
    private final LoadAccountPort loadAccountPort;

    @Override
    public Account fetchAccountByIdAndDateRange(Integer accountId) {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(14);
        return loadAccountPort.loadAccount(new Account.AccountId(accountId), localDateTime);
    }
}
