package com.mariesto.buckpal.account.adapter.out.persistence;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;
import com.mariesto.buckpal.account.application.port.out.CreateAccountPort;
import com.mariesto.buckpal.account.application.port.out.LoadAccountPort;
import com.mariesto.buckpal.account.application.port.out.UpdateAccountStatePort;
import com.mariesto.buckpal.account.domain.Activity;
import com.mariesto.buckpal.account.domain.Account;
import com.mariesto.buckpal.account.domain.Account.AccountId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort, CreateAccountPort {
    private final AccountRepository accountRepository;

    private final ActivityRepository activityRepository;

    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(AccountId accountId, LocalDateTime time) {
        AccountJpaEntity account = accountRepository.findAccountJpaEntitiesByAccountId(accountId.getValue());
        List<ActivityJpaEntity> activities = activityRepository.findByOwnerSince(accountId.getValue(), Timestamp.valueOf(time));
        Long withdrawalBalance = orZero(activityRepository.getWithdrawalBalanceUntil(accountId.getValue(), Timestamp.valueOf(time)));
        Long depositBalance = orZero(activityRepository.getDepositBalanceUntil(accountId.getValue(), Timestamp.valueOf(time)));
        return accountMapper.mapToDomainEntity(account, activities, withdrawalBalance, depositBalance);
    }

    @Override
    public void updateActivities(Account account) {
        for (Activity activity : account.getActivityWindow().getActivities()) {
            if (activity.getId() == null) {
                ActivityJpaEntity activityJpa = accountMapper.mapToJpaEntity(activity);
                activityRepository.save(activityJpa);
            }
        }
    }

    private Long orZero(Long value) {
        return value == null ? 0L : value;
    }

    @Override
    public Account createAccount(Account account) {
        accountMapper.mapToJpaEntity(account);
        return account;
    }
}
