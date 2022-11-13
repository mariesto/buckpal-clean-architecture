package application.account.adapter.out.persistence;

import static application.account.domain.Account.AccountId;
import static org.assertj.core.api.Assertions.assertThat;
import static application.common.TestUtil.defaultActivity;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import application.account.domain.Account;
import application.account.domain.ActivityWindow;
import application.account.domain.Money;
import application.common.TestUtil;

@ActiveProfiles ("test")
@DataJpaTest
@Import ({ AccountPersistenceAdapter.class, AccountMapper.class })
class AccountPersistenceAdapterTest {
    @Autowired
    private AccountPersistenceAdapter persistenceAdapter;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql (scripts = "import.sql")
    void loadAccount() {
        Account account = persistenceAdapter.loadAccount(new AccountId(1), LocalDateTime.of(2018, 8, 10, 0, 0));

        assertThat(account.getActivityWindow().getActivities()).hasSize(2);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(500));
    }

    @Test
    void updateActivities() {
        Account account = TestUtil.defaultAccount().withBaselineBalance(Money.of(555L))
                                  .withActivityWindow(new ActivityWindow(defaultActivity().withMoney(Money.of(1L)).build())).build();
        persistenceAdapter.updateActivities(account);
        assertThat(activityRepository.findAll()).hasSize(1);

        ActivityJpaEntity savedActivity = activityRepository.findAll().get(0);
        assertThat(savedActivity.getAmount()).isEqualTo(1L);
    }

}