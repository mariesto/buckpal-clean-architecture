package com.mariesto.buckpal.account.application.service;

import static com.mariesto.buckpal.account.domain.Account.AccountId;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.mariesto.buckpal.account.application.port.in.sendmoney.SendMoneyCommand;
import com.mariesto.buckpal.account.application.port.out.AccountLock;
import com.mariesto.buckpal.account.application.port.out.LoadAccountPort;
import com.mariesto.buckpal.account.application.port.out.UpdateAccountStatePort;
import com.mariesto.buckpal.account.domain.Account;
import com.mariesto.buckpal.account.domain.Money;

class SendMoneyServiceTest {
    @Mock
    private LoadAccountPort loadAccountPort;

    @Mock
    private AccountLock accountLock;

    @Mock
    private UpdateAccountStatePort updateAccountStatePort;

    @Mock
    private SendMoneyService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new SendMoneyService(accountLock, loadAccountPort, updateAccountStatePort);
    }

    @Test
    void sendMoney() {
        Account sourceAccount = givenSourceAccount();
        Account destinationAccount = givenDestinationAccount();

        given(sourceAccount.withdraw(any(Money.class), any(AccountId.class))).willReturn(true);
        given(destinationAccount.deposit(any(Money.class), any(AccountId.class))).willReturn(true);

        Money money = Money.of(500L);

        SendMoneyCommand command = new SendMoneyCommand(sourceAccount.getId().get(), destinationAccount.getId().get(), money);

        boolean success = service.sendMoney(command);
        assertTrue(success);

        AccountId sourceAccountId = sourceAccount.getId().get();
        AccountId destinationAccountId = destinationAccount.getId().get();

        then(accountLock).should().lockAccount(eq(sourceAccountId));
        then(sourceAccount).should().withdraw(eq(money), eq(destinationAccountId));
        then(accountLock).should().releaseAccount(eq(sourceAccountId));

        then(accountLock).should().lockAccount(eq(destinationAccountId));
        then(destinationAccount).should().deposit(eq(money), eq(sourceAccountId));
        then(accountLock).should().releaseAccount(eq(destinationAccountId));

        thenAccountsHaveBeenUpdated(sourceAccountId, destinationAccountId);
    }

    private void thenAccountsHaveBeenUpdated(AccountId... accountIds) {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        then(updateAccountStatePort).should(times(accountIds.length)).updateActivities(accountCaptor.capture());

        List<AccountId> updatedAccountIds = accountCaptor.getAllValues().stream().map(Account::getId).map(Optional::get).collect(Collectors.toList());

        for (AccountId accountId : accountIds) {
            assertTrue(updatedAccountIds.contains(accountId));
        }
    }

    private Account givenDestinationAccount(){
        return givenAnAccountWithId(new AccountId(42));
    }

    private Account givenSourceAccount(){
        return givenAnAccountWithId(new AccountId(41));
    }

    private Account givenAnAccountWithId(AccountId id) {
        Account account = Mockito.mock(Account.class);
        given(account.getId()).willReturn(Optional.of(id));
        given(loadAccountPort.loadAccount(eq(account.getId().get()), any(LocalDateTime.class))).willReturn(account);
        return account;
    }

}