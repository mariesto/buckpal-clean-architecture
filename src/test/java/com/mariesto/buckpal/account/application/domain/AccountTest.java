package com.mariesto.buckpal.account.application.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.mariesto.buckpal.account.domain.Account;
import com.mariesto.buckpal.account.domain.ActivityWindow;
import com.mariesto.buckpal.account.domain.Money;
import com.mariesto.buckpal.common.TestUtil;

class AccountTest {

    @Test
    void givenAnObject_whenWithdrawMoney_shouldReturnSuccess() {
        Account.AccountId accountId = new Account.AccountId(1);
        Account account = TestUtil.defaultAccount().withAccountId(accountId).withBaselineBalance(Money.of(5000L)).withActivityWindow(
                new ActivityWindow(TestUtil.defaultActivity().withTargetAccount(accountId).withMoney(Money.of(1000L)).build(),
                        TestUtil.defaultActivity().withTargetAccount(accountId).withMoney(Money.of(1000L)).build())).build();
        boolean withdraw = account.withdraw(Money.of(1000L), new Account.AccountId(2));
        assertTrue(withdraw);
        assertEquals(3, account.getActivityWindow().getActivities().size());
        assertEquals(Money.of(6000), account.calculateBalance());
    }
}