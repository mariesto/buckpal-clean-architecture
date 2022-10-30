package account.adapter.web.in;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import account.application.port.in.createaccount.CreateAccountCommand;
import account.application.port.in.createaccount.CreateAccountUseCase;
import account.application.port.in.fetchaccount.GetAccountBalanceQuery;
import account.application.port.in.fetchaccount.ListAccountsQuery;
import account.domain.Account;
import account.domain.AccountRequest;
import account.domain.Money;
import lombok.RequiredArgsConstructor;

@RestController ("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final GetAccountBalanceQuery getAccountBalanceQuery;
    private final ListAccountsQuery listAccountsQuery;

    private final CreateAccountUseCase createAccountUseCase;

    @GetMapping
    List<AccountRequest> listAccounts(){
        return listAccountsQuery.listAccounts();
    }

    @GetMapping("/{account_id}}")
    AccountRequest getAccountById(@PathVariable("account_id") Long accountId){
        return listAccountsQuery.fetchAccountByIdAndDateRange(accountId);
    }

    @GetMapping("/{account_id}/balance")
    Money getAccountBalance(@PathVariable("account_id") Long accountId){
        return getAccountBalanceQuery.getAccountBalance(new Account.AccountId(accountId));
    }

    @PostMapping
    void createAccount(@RequestBody AccountRequest accountRequest){
        CreateAccountCommand command = new CreateAccountCommand(accountRequest.getMoney());
        createAccountUseCase.createAccount(command);
    }
}
