package application.account.adapter.in.web.in;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import application.account.application.port.in.createaccount.CreateAccountCommand;
import application.account.application.port.in.createaccount.CreateAccountUseCase;
import application.account.application.port.in.fetchaccount.GetAccountBalanceQuery;
import application.account.application.port.in.fetchaccount.ListAccountsQuery;
import application.account.domain.Account;
import application.account.domain.AccountRequest;
import application.account.domain.Money;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final GetAccountBalanceQuery getAccountBalanceQuery;

    private final ListAccountsQuery listAccountsQuery;

    private final CreateAccountUseCase createAccountUseCase;

    @GetMapping (path = "/accounts/{account_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountById(@PathVariable ("account_id") Integer accountId) {
        Account account = listAccountsQuery.fetchAccountByIdAndDateRange(accountId);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping ("/accounts/{account_id}/balance")
    Money getAccountBalance(@PathVariable ("account_id") Integer accountId) {
        return getAccountBalanceQuery.getAccountBalance(new Account.AccountId(accountId));
    }

    @PostMapping
    void createAccount(@RequestBody AccountRequest accountRequest) {
        CreateAccountCommand command = new CreateAccountCommand(accountRequest.getMoney());
        createAccountUseCase.createAccount(command);
    }
}
