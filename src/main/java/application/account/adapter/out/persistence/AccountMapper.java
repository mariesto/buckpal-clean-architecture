package application.account.adapter.out.persistence;

import static application.account.domain.Account.AccountId;
import static application.account.domain.Account.withId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import application.account.domain.Account;
import application.account.domain.Activity;
import application.account.domain.ActivityWindow;
import application.account.domain.Money;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    Account mapToDomainEntity(AccountJpaEntity account, List<ActivityJpaEntity> activities, Long withdrawalBalance, Long depositBalance) {
        Money baselineBalance = Money.subtract(Money.of(depositBalance), Money.of(withdrawalBalance));
        return withId(new AccountId(account.getAccountId()), baselineBalance, mapToActivityWindow(activities));
    }

    ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();
        for (ActivityJpaEntity activity : activities) {
            mappedActivities.add(new Activity(new Activity.ActivityId(activity.getId()), new AccountId(activity.getOwnerAccountId()),
                    new AccountId(activity.getSourceAccountId()), new AccountId(activity.getDestinationAccountId()), activity.getTimeStamp(),
                    Money.of(activity.getAmount())));
        }

        return new ActivityWindow(mappedActivities);
    }

    ActivityJpaEntity mapToJpaEntity(Activity activity) {
        return new ActivityJpaEntity(activity.getId() == null ? null : activity.getId().getValue(), activity.getTimestamp(),
                activity.getOwnerAccountId().getValue(), activity.getSourceAccountId().getValue(), activity.getDestinationAccountId().getValue(),
                activity.getMoney().getAmount().longValue());
    }

    AccountJpaEntity mapToJpaEntity(Account account){
        return new AccountJpaEntity(account.getId().get().getValue());
    }
}
