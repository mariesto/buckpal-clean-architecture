package application.account.application.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import application.account.application.port.in.fetchaccount.GetAccountBalanceQuery;
import application.account.application.port.out.LoadAccountPort;
import application.account.domain.Account;
import application.account.domain.Money;
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
