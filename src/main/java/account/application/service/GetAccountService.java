package account.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import account.application.port.in.fetchaccount.ListAccountsQuery;
import account.application.port.out.LoadAccountPort;
import account.domain.Account;
import account.domain.AccountRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAccountService implements ListAccountsQuery {
    private final LoadAccountPort loadAccountPort;

    private final ModelMapper modelMapper;

    @Override
    public List<AccountRequest> listAccounts() {
        List<Account> accounts = loadAccountPort.loadAllAccounts();
        return accounts.stream()
                       .map(account -> modelMapper.map(account, AccountRequest.class))
                       .collect(Collectors.toList());
    }

    @Override
    public AccountRequest fetchAccountByIdAndDateRange(Long accountId) {
        Account account = loadAccountPort.loadAccount(new Account.AccountId(accountId), LocalDateTime.now().minusDays(14));
        return modelMapper.map(account, AccountRequest.class);
    }
}
